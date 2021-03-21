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
public class OSSystemEnvPropertiesConfigSource extends AbstractMapConfigSource {

    private static final String NAME = "OS Environment Properties";

    public OSSystemEnvPropertiesConfigSource() {
        super(NAME, 300);
    }

    @Override
    protected void prepareConfigData(Map map) throws Throwable {
        map.putAll(System.getenv());
    }
}
