package org.geektimes.configuration.microprofile.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * byte
 *
 * @author ViJay
 * @date 2021/3/14 20:28
 */
public class ByteConverter extends AbstractConverter<Byte> {
    @Override
    public Byte doConverter(String s) throws IllegalArgumentException, NullPointerException {
        return Byte.parseByte(s);
    }
}
