package org.geektimes.configuration.microprofile.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * int
 *
 * @author ViJay
 * @date 2021/3/14 20:29
 */
public class IntegerConverter implements Converter<Integer> {
    @Override
    public Integer convert(String s) throws IllegalArgumentException, NullPointerException {
        return Integer.parseInt(s);
    }
}
