package org.geektimes.configuration.microprofile.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * int
 *
 * @author ViJay
 * @date 2021/3/14 20:29
 */
public class IntegerConverter extends AbstractConverter<Integer> {
    @Override
    public Integer doConverter(String s) throws IllegalArgumentException, NullPointerException {
        return Integer.parseInt(s);
    }
}
