package org.geektimes.configuration.microprofile.config.provider;

import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.ConfigSourceProvider;
import org.geektimes.configuration.microprofile.config.source.ApplicationFilePropertiesConfigSource;

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 *  files read properties from  application.properties
 *
 * @author ViJay
 * @date 2021/3/21 0:04
 */
public class ApplicationConfigSourceProvider implements ConfigSourceProvider {

    @Override
    public Iterable<ConfigSource> getConfigSources(ClassLoader forClassLoader) {
        List<ConfigSource> configSourceList = new ArrayList<>();
        try {
            Enumeration<URL> resources = forClassLoader.getResources("application.properties");
            while (resources.hasMoreElements()){
                URL url = resources.nextElement();
                ApplicationFilePropertiesConfigSource configSource = new ApplicationFilePropertiesConfigSource(url);
                configSourceList.add(configSource);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return configSourceList;
    }
}
