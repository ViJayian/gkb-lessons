package org.geektimes.configuration.microprofile.config.source;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 本地配置文件
 *
 * @author ViJay
 * @date 2021/3/14 21:54
 */
public class LocalFilePropertiesConfigSource implements ConfigSource {

    private final Map<String, String> properties;
    private static final String FILE_PATH = "application.properties";
    private static final String NAME = "Local File Properties";


    /**
     * 从resouce下application.properties获取配置
     */
    public LocalFilePropertiesConfigSource() {
        this.properties = new HashMap<>();
        loadProperties();
    }

    private void loadProperties() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(FILE_PATH);
        Properties properties = new Properties();
        try {
            properties.load(resource.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<Map.Entry<Object, Object>> entries = properties.entrySet();
        for (Map.Entry<Object, Object> entry : entries) {
            this.properties.put((String) entry.getKey(), (String) entry.getValue());
        }
    }

    @Override
    public Set<String> getPropertyNames() {
        return this.properties.keySet();
    }

    @Override
    public String getValue(String s) {
        return this.properties.get(s);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
