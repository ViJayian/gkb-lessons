package org.geektimes.configuration.microprofile.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * FloatConverters
 *
 * @author ViJay
 * @date 2021/3/14 21:33
 */
public class FloatConverter extends AbstractConverter<Float> {
    @Override
    public Float doConverter(String s) throws IllegalArgumentException, NullPointerException {
        return Float.parseFloat(s);
    }
}
