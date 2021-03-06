package org.geektimes.configuration.microprofile.config.source;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Comparator;

/**
 * ConfigSourceOrdinalComparator
 *
 * @author ViJay
 * @date 2021/3/21 11:09
 */
public class ConfigSourceOrdinalComparator implements Comparator<ConfigSource> {

    public static final ConfigSourceOrdinalComparator INSTANCE = new ConfigSourceOrdinalComparator();

    private ConfigSourceOrdinalComparator() {

    }

    @Override
    public int compare(ConfigSource o1, ConfigSource o2) {
        return Integer.compare(o2.getOrdinal(), o1.getOrdinal());
    }
}
