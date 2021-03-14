package org.geektimes.configuration.microprofile.config.source;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Map;
import java.util.Set;

/**
 * 系统环境变量
 *
 * @author ViJay
 * @date 2021/3/14 21:48
 */
public class OSSystemEnvPropertiesConfigSource implements ConfigSource {

    private static final String NAME = "OS Environment Properties";
    private final Map<String, String> propertiesMap;

    public OSSystemEnvPropertiesConfigSource() {
        this.propertiesMap = System.getenv();
    }

    @Override
    public Set<String> getPropertyNames() {
        return propertiesMap.keySet();
    }

    @Override
    public String getValue(String s) {
        return propertiesMap.get(s);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
