package org.geektimes.configuration.microprofile.config.source;

import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.ConfigSourceProvider;

import java.util.*;

import static java.util.Collections.sort;
import static java.util.ServiceLoader.load;
import static java.util.stream.Stream.of;

/**
 * configsources
 *
 * @author ViJay
 * @date 2021/3/21 9:41
 */
public class ConfigSources implements Iterable<ConfigSource> {

    private boolean addedDefaultConfigSources;

    private boolean addedDiscoveredConfigSources;

    private List<ConfigSource> configSources = new LinkedList<>();

    private ClassLoader classLoader;

    public ConfigSources(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    /**
     * 文档中 System properties,env和META-INF/microprofile-config.properties为默认源
     */
    public void addDefaultSources() {
        if (addedDefaultConfigSources) {
            return;
        }
        addConfigSources(JavaSystemPropertiesConfigSource.class,
                OSSystemEnvPropertiesConfigSource.class,
                LocalFilePropertiesConfigSource.class
        );
        addedDefaultConfigSources = true;
    }

    /**
     * discover为spi加载或者
     * ConfigSourceProvider的实现
     */
    public void addDiscoveredSources() {
        if (addedDiscoveredConfigSources) {
            return;
        }
        addConfigSources(load(ConfigSource.class, classLoader));
        addConfigSourcesByProvider(load(ConfigSourceProvider.class, classLoader));
        addedDiscoveredConfigSources = true;
    }

    private void addConfigSourcesByProvider(Iterable<ConfigSourceProvider> configSourceProviders) {
        configSourceProviders.forEach(configSourceProvider -> {
            Iterable<ConfigSource> configSources = configSourceProvider.getConfigSources(this.classLoader);
            configSources.forEach(this.configSources::add);
        });
        sort(this.configSources, ConfigSourceOrdinalComparator.INSTANCE);
    }

    public void addConfigSources(Class<? extends ConfigSource>... configSourceClasses) {
        addConfigSources(
                of(configSourceClasses)
                        .map(this::newInstance)
                        .toArray(ConfigSource[]::new)
        );
    }

    public void addConfigSources(ConfigSource... configSources) {
        addConfigSources(Arrays.asList(configSources));
    }

    public void addConfigSources(Iterable<ConfigSource> configSources) {
        configSources.forEach(this.configSources::add);
        sort(this.configSources, ConfigSourceOrdinalComparator.INSTANCE);
    }

    private ConfigSource newInstance(Class<? extends ConfigSource> configSourceClass) {
        ConfigSource instance = null;
        try {
            instance = configSourceClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
        return instance;
    }

    @Override
    public Iterator<ConfigSource> iterator() {
        return configSources.iterator();
    }

    public boolean isAddedDefaultConfigSources() {
        return addedDefaultConfigSources;
    }

    public boolean isAddedDiscoveredConfigSources() {
        return addedDiscoveredConfigSources;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

}
