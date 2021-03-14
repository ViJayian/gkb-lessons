package org.geektimes.configuration.microprofile.converter;

import org.eclipse.microprofile.config.spi.Converter;

import java.math.BigDecimal;

/**
 * bigdecimal
 *
 * @author ViJay
 * @date 2021/3/14 21:36
 */
public class BigDecimalConverter implements Converter<BigDecimal> {
    @Override
    public BigDecimal convert(String s) throws IllegalArgumentException, NullPointerException {
        return new BigDecimal(s);
    }
}
