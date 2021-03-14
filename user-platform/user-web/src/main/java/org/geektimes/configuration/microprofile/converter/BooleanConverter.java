package org.geektimes.configuration.microprofile.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * boolean converter
 *
 * @author ViJay
 * @date 2021/3/14 20:25
 */
public class BooleanConverter implements Converter<Boolean> {
    @Override
    public Boolean convert(String s) throws IllegalArgumentException, NullPointerException {
        return Boolean.parseBoolean(s);
    }
}
