package org.geektimes.configuration.microprofile.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * DoubleConverters
 *
 * @author ViJay
 * @date 2021/3/14 21:32
 */
public class DoubleConverter implements Converter<Double> {
    @Override
    public Double convert(String s) throws IllegalArgumentException, NullPointerException {
        return Double.parseDouble(s);
    }
}