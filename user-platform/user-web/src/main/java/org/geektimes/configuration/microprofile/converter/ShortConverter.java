package org.geektimes.configuration.microprofile.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * short
 *
 * @author ViJay
 * @date 2021/3/14 20:27
 */
public class ShortConverter implements Converter<Short> {
    @Override
    public Short convert(String s) throws IllegalArgumentException, NullPointerException {
        return Short.parseShort(s);
    }
}
