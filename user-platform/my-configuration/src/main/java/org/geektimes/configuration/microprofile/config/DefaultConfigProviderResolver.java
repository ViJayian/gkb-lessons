package org.geektimes.configuration.microprofile.config;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * resolver
 *
 * @author ViJay
 * @date 2021/3/14 19:40
 */
public class DefaultConfigProviderResolver extends ConfigProviderResolver {

    private ConcurrentHashMap<ClassLoader, Config> configsRepository = new ConcurrentHashMap<>();

    @Override
    public Config getConfig() {
        return getConfig(null);
    }

    /**
     * SPI (Service Provider Interface) Java提供的一套用于被第三方实现或者扩展的api
     *
     * @param classLoader
     * @return
     */
    @Override
    public Config getConfig(ClassLoader classLoader) {
        return configsRepository.computeIfAbsent(classLoader, this::newConfig);
        /*ClassLoader loader = classLoader;
        if (loader == null) {
            loader = Thread.currentThread().getContextClassLoader();
        }
        //Spi加载Config的所有实现
        ServiceLoader<Config> serviceLoader = ServiceLoader.load(Config.class, loader);
        Iterator<Config> iterator = serviceLoader.iterator();
        if (iterator.hasNext()) {
            // 获取Config spi的第一个实现
            return iterator.next();
        }
        throw new IllegalStateException("No Config Found");*/
    }

    protected Config newConfig(ClassLoader classLoader) {
        return newConfigBuilder(classLoader).build();
    }

    private ConfigBuilder newConfigBuilder(ClassLoader classLoader) {
        return new DefaultConfigBuilder(resolveClassLoader(classLoader));
    }

    @Override
    public ConfigBuilder getBuilder() {
        return newConfigBuilder(null);
    }

    private ClassLoader resolveClassLoader(ClassLoader classLoader) {
        return classLoader == null ? this.getClass().getClassLoader() : classLoader;
    }

    @Override
    public void registerConfig(Config config, ClassLoader classLoader) {
        configsRepository.put(classLoader, config);
    }

    @Override
    public void releaseConfig(Config config) {
        List<ClassLoader> targetKeys = new LinkedList<>();
        for (Map.Entry<ClassLoader, Config> entry : configsRepository.entrySet()) {
            if (Objects.equals(config, entry.getValue())) {
                targetKeys.add(entry.getKey());
            }
        }
        targetKeys.forEach(configsRepository::remove);
    }
}
