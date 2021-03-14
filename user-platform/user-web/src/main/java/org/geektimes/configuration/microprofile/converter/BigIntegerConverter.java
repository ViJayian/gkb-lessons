package org.geektimes.configuration.microprofile.converter;

import org.eclipse.microprofile.config.spi.Converter;

import java.math.BigInteger;

/**
 * big integer
 *
 * @author ViJay
 * @date 2021/3/14 21:37
 */
public class BigIntegerConverter implements Converter<BigInteger> {
    @Override
    public BigInteger convert(String s) throws IllegalArgumentException, NullPointerException {
        return new BigInteger(s);
    }
}
