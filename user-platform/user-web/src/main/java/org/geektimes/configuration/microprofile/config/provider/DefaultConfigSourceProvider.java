package org.geektimes.configuration.microprofile.config.provider;

import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.ConfigSourceProvider;

/**
 * ConfigSourceProvider
 *
 * @author ViJay
 * @date 2021/3/21 0:04
 */
public class DefaultConfigSourceProvider implements ConfigSourceProvider {

    // 返回所有的ConfigSource
    @Override
    public Iterable<ConfigSource> getConfigSources(ClassLoader forClassLoader) {
        return null;
    }
}
