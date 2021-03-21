package org.geektimes.configuration.microprofile.config.source;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.io.IOException;
import java.io.InputStream;
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
public class LocalFilePropertiesConfigSource extends AbstractMapConfigSource {

    private static final String FILE_PATH = "META-INF/microprofile-config.properties";
    private static final String NAME = "Local File Properties";

    protected LocalFilePropertiesConfigSource() {
        super(NAME, 100);
    }

    //加载文件配置
    @Override
    protected void prepareConfigData(Map map) throws Throwable {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(FILE_PATH);
        Properties properties = new Properties();
        try (InputStream inputStream = resource.openStream()) {
            properties.load(inputStream);
            map.putAll(properties);
        }
    }
}
