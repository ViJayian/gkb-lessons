package org.geektimes.configuration.microprofile.config;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * resolver
 *
 * @author ViJay
 * @date 2021/3/14 19:40
 */
public class DefaultConfigProviderResolver extends ConfigProviderResolver {
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
        ClassLoader loader = classLoader;
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
        throw new IllegalStateException("No Config Found");
    }

    @Override
    public ConfigBuilder getBuilder() {
        return null;
    }

    @Override
    public void registerConfig(Config config, ClassLoader classLoader) {

    }

    @Override
    public void releaseConfig(Config config) {

    }
}
