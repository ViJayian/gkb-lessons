package org.geektimes.configuration.microprofile.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * short
 *
 * @author ViJay
 * @date 2021/3/14 20:27
 */
public class ShortConverter extends AbstractConverter<Short> {
    @Override
    public Short doConverter(String s) throws IllegalArgumentException, NullPointerException {
        return Short.parseShort(s);
    }
}
