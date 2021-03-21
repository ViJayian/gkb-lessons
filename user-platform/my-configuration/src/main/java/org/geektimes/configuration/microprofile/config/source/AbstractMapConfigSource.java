package org.geektimes.configuration.microprofile.config.source;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 基于map的ConfigSource
 *
 * @author ViJay
 * @date 2021/3/20 17:37
 */
public abstract class AbstractMapConfigSource implements ConfigSource {

    private final String name;
    private final Integer ordinal;
    private final Map<String, String> sources;

    protected AbstractMapConfigSource(String name, Integer ordinal) {
        this.name = name;
        this.ordinal = ordinal;
        this.sources = getProperties();
    }

    /**
     * template pattern 提供外部实现，添加数据
     */
    protected abstract void prepareConfigData(Map map) throws Throwable;

    @Override
    public Set<String> getPropertyNames() {
        return sources.keySet();
    }

    @Override
    public String getValue(String propertyName) {
        return sources.get(propertyName);
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * @return
     */
    @Override
    public final Map<String, String> getProperties() {
        Map<String, String> configurationMaps = new HashMap<>();
        try {
            prepareConfigData(configurationMaps);
        } catch (Throwable cause) {
            throw new IllegalStateException("load org.geektimes.configuration exception", cause);
        }
        return Collections.unmodifiableMap(configurationMaps);
    }

    @Override
    public int getOrdinal() {
        return ordinal;
    }
}
