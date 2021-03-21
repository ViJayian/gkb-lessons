package org.geektimes.configuration.microprofile.config.source;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * appliction properties 通过ConfigSourceProvider注册
 *
 * @author ViJay
 * @date 2021/3/21 10:27
 */
public class ApplicationFilePropertiesConfigSource implements ConfigSource {

    private static final String NAME = "application config";

    private Map properties = new HashMap();

    public ApplicationFilePropertiesConfigSource(URL url) throws Throwable {
        Properties properties = new Properties();
        try (InputStream inputStream = url.openStream()) {
            properties.load(inputStream);
            this.properties.putAll(properties);
        }
    }

    @Override
    public Set<String> getPropertyNames() {
        return properties.keySet();
    }

    @Override
    public String getValue(String propertyName) {
        return (String) properties.get(propertyName);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int getOrdinal() {
        return 500;
    }
}
