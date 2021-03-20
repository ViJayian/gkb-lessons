package org.geektimes.configuration.microprofile.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * LongConverters
 *
 * @author ViJay
 * @date 2021/3/14 21:32
 */
public class LongConverter extends AbstractConverter<Long> {
    @Override
    public Long doConverter(String s) throws IllegalArgumentException, NullPointerException {
        return Long.parseLong(s);
    }
}