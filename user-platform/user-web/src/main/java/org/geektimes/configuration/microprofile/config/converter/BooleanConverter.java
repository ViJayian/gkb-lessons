package org.geektimes.configuration.microprofile.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * boolean converter
 *
 * @author ViJay
 * @date 2021/3/14 20:25
 */
public class BooleanConverter extends AbstractConverter<Boolean> {
    @Override
    public Boolean doConverter(String s) throws IllegalArgumentException, NullPointerException {
        return Boolean.parseBoolean(s);
    }
}
