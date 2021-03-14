package org.geektimes.configuration.microprofile.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * FloatConverters
 *
 * @author ViJay
 * @date 2021/3/14 21:33
 */
public class FloatConverter implements Converter<Float> {
    @Override
    public Float convert(String s) throws IllegalArgumentException, NullPointerException {
        return Float.parseFloat(s);
    }
}
