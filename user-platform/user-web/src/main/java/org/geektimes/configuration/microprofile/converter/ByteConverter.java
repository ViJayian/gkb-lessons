package org.geektimes.configuration.microprofile.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * byte
 *
 * @author ViJay
 * @date 2021/3/14 20:28
 */
public class ByteConverter implements Converter<Byte> {
    @Override
    public Byte convert(String s) throws IllegalArgumentException, NullPointerException {
        return Byte.parseByte(s);
    }
}
