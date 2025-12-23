package com.kravchenko.contentfilter.parser;

import com.kravchenko.contentfilter.model.DataType;
import com.kravchenko.contentfilter.model.StringData;
import java.util.Optional;

/**
 * Парсер для строковых данных.
 */
public final class StringParser implements Parser {

    @Override
    public Optional<DataType> parse(final String line) {
        if (line == null) {
            return Optional.empty();
        }
        return Optional.of(new StringData(line));
    }
}
