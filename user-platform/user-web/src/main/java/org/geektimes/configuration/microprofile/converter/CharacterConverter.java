package org.geektimes.configuration.microprofile.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * CharacterConverter
 *
 * @author ViJay
 * @date 2021/3/14 21:33
 */
public class CharacterConverter implements Converter<Character> {
    @Override
    public Character convert(String s) throws IllegalArgumentException, NullPointerException {
        return s.charAt(0);
    }
}
