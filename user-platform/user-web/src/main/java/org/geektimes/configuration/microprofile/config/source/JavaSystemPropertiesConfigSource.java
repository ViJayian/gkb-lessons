package org.geektimes.configuration.microprofile.config.source;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.*;

/**
 * java system config
 *
 * @author ViJay
 * @date 2021/3/14 19:12
 */
public class JavaSystemPropertiesConfigSource implements ConfigSource {

    private final Map<String, String> properties;
    private static final String PROPERTIES_NAME = "Java System Properties";

    /**
     * java 系统环境变量
     * 使用Map存储，Properties中getProperties方法底层调用的是synchronized方法
     * {@link Properties#getProperty(java.lang.String)}
     * {@link Hashtable#get(java.lang.Object)}
     *
     */
    public JavaSystemPropertiesConfigSource() {
        Map systemProperties = System.getProperties();
        this.properties = new HashMap<>(systemProperties);
    }

    /**
     * Properties Name
     *
     * @return
     */
    @Override
    public Set<String> getPropertyNames() {
        return properties.keySet();
    }

    @Override
    public String getValue(String s) {
        return this.properties.get(s);
    }

    @Override
    public String getName() {
        return PROPERTIES_NAME;
    }
}
