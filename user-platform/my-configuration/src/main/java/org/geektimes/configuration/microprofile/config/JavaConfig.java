package org.geektimes.configuration.microprofile.config;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigValue;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.Converter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Stream;

/**
 * Config配置门面
 *
 * @author ViJay
 * @date 2021/3/14 19:39
 */
public class JavaConfig implements Config {

    private List<ConfigSource> configSourceList;

    private static Comparator<ConfigSource> comparator = (o1, o2) -> o2.getOrdinal() - o1.getOrdinal();

    private Map<String, Converter> converterMap;

    public JavaConfig() {
        configSourceList = new LinkedList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        // init config source
        initConfigSources(classLoader);
        // init converters
        initConverters(classLoader);
    }

    /**
     * 初始化configSource
     *
     * @param classLoader
     */
    private void initConfigSources(ClassLoader classLoader) {
        ServiceLoader<ConfigSource> serviceLoader = ServiceLoader.load(ConfigSource.class, classLoader);
        serviceLoader.forEach(configSourceList::add);
        // Config按照Ordinal排序
        sortConfigSourceListByOrdinal();
    }

    /**
     * 初始化Converters
     *
     * @param classLoader
     */
    private void initConverters(ClassLoader classLoader) {
        converterMap = new HashMap<>();
        ServiceLoader<Converter> serviceLoader = ServiceLoader.load(Converter.class, classLoader);
        Iterator<Converter> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            Converter converter = iterator.next();
            // 获取当前Converter的Class类型
            Type genericSuperclass = converter.getClass().getGenericSuperclass();
            Class converterClass = (Class)(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
            // 接口类型
            /*Type[] genericInterfaces = converter.getClass().getGenericInterfaces();
            Class converterClass = (Class) ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments()[0];*/
            String converterName = converterClass.getSimpleName();
            converterMap.put(converterName, converter);
        }
    }

    private void sortConfigSourceListByOrdinal() {
        this.configSourceList.sort(comparator);
    }

    @Override
    public <T> T getValue(String propertyName, Class<T> aClass) {
        String propertyValue = getPropertyValue(propertyName);
        //String 转换为目标类型
        if(aClass == String.class){
            return (T) propertyValue;
        }
        Converter<T> converter = getConverter(aClass).orElseThrow(NoSuchElementException::new);
        return converter.convert(propertyValue);
    }

    private String getPropertyValue(String propertyName) {
        for (ConfigSource configSource : configSourceList) {
            String value = configSource.getValue(propertyName);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    @Override
    public ConfigValue getConfigValue(String s) {
        return null;
    }

    @Override
    public <T> Optional<T> getOptionalValue(String s, Class<T> aClass) {
        return Optional.ofNullable(getValue(s, aClass));
    }

    @Override
    public Iterable<String> getPropertyNames() {
        return null;
    }

    /**
     * {@link Collections#unmodifiableList(java.util.List)}
     * - throw new UnsupportedOperationException();
     *
     * @return
     */
    @Override
    public Iterable<ConfigSource> getConfigSources() {
        // 不要暴露给外部可以修改的内容
        List<ConfigSource> configSources = Collections.unmodifiableList(this.configSourceList);
        // configSources.add(null);
        return configSources;
    }

    @Override
    public <T> Optional<Converter<T>> getConverter(Class<T> aClass) {
        String name = aClass.getSimpleName();
        Stream<String> keyStream = converterMap.keySet().stream();
        Optional<String> converterKey = keyStream.filter(val -> val.equalsIgnoreCase(name)).findFirst();
        String key = converterKey.orElseThrow(NoSuchElementException::new);
        Converter<T> converter = converterMap.get(key);
        return Optional.ofNullable(converter);
    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        return null;
    }
}
