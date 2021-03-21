package org.geektimes.configuration.microprofile.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

import java.math.BigDecimal;

/**
 * bigdecimal
 *
 * @author ViJay
 * @date 2021/3/14 21:36
 */
public class BigDecimalConverter extends AbstractConverter<BigDecimal>{

    @Override
    public BigDecimal doConverter(String s) throws IllegalArgumentException, NullPointerException {
        return new BigDecimal(s);
    }
}
