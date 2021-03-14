package org.geektimes.configuration.microprofile.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * LongConverters
 *
 * @author ViJay
 * @date 2021/3/14 21:32
 */
public class LongConverter implements Converter<Long> {
    @Override
    public Long convert(String s) throws IllegalArgumentException, NullPointerException {
        return Long.parseLong(s);
    }
}