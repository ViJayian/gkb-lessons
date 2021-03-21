package org.geektimes.configuration.microprofile.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

import java.math.BigInteger;

/**
 * big integer
 *
 * @author ViJay
 * @date 2021/3/14 21:37
 */
public class BigIntegerConverter extends AbstractConverter<BigInteger> {
    @Override
    public BigInteger doConverter(String s) throws IllegalArgumentException, NullPointerException {
        return new BigInteger(s);
    }
}
