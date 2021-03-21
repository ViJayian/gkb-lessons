package org.geektimes.microprofile.config;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;
import org.junit.Test;

/**
 * 测试配置类
 *
 * @author ViJay
 * @date 2021/3/21 10:36
 */
public class TestMicroProfile {

    @Test
    public void testConfig() {
        ConfigProviderResolver resolver = ConfigProviderResolver.instance();
        ConfigBuilder builder = resolver.getBuilder();
        builder.addDefaultSources();
//        builder.addDiscoveredSources();
        builder.addDiscoveredConverters();
        Config config = builder.build();
        String applicationName = config.getValue("application.name", String.class);
        Integer port = config.getValue("server.port", Integer.class);
        System.out.printf("application name is [%s], port = [%s]", applicationName, port);

    }

    // configSourceProvider新增配置
    /*@Test
    public void testConfigSourceProvider() {
        ConfigSourceProvider provider = new ApplicationConfigSourceProvider();
        Iterable<ConfigSource> configSources = provider.getConfigSources(getClass().getClassLoader());
        System.out.println(configSources);
    }*/
}
