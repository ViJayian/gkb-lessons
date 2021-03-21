package org.geektimes.configuration.microprofile.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * CharacterConverter
 *
 * @author ViJay
 * @date 2021/3/14 21:33
 */
public class CharacterConverter extends AbstractConverter<Character> {
    @Override
    public Character doConverter(String s) throws IllegalArgumentException, NullPointerException {
        return s.charAt(0);
    }
}
