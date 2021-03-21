package org.geektimes.configuration.microprofile.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

import java.util.Objects;

/**
 * abstract converter
 * {@link Converter}
 *
 * @author ViJay
 * @date 2021/3/20 17:19
 */
public abstract class AbstractConverter<T> implements Converter<T> {
    @Override
    public T convert(String s) throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(s, () -> "value is null");
        return doConverter(s);
    }

    protected abstract T doConverter(String s);
}
