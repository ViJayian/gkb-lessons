package org.geektimes.configuration.microprofile.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * DoubleConverters
 *
 * @author ViJay
 * @date 2021/3/14 21:32
 */
public class DoubleConverter extends AbstractConverter<Double> {
    @Override
    public Double doConverter(String s) throws IllegalArgumentException, NullPointerException {
        return Double.parseDouble(s);
    }
}