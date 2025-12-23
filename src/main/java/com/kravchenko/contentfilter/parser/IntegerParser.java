package com.kravchenko.contentfilter.parser;

import com.kravchenko.contentfilter.model.DataType;
import com.kravchenko.contentfilter.model.IntegerData;
import java.math.BigInteger;
import java.util.Optional;

/**
 * Парсер для целочисленных данных.
 */
public final class IntegerParser implements Parser {

    @Override
    public Optional<DataType> parse(final String line) {
        try {
            return Optional.of(new IntegerData(new BigInteger(line.trim())));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
