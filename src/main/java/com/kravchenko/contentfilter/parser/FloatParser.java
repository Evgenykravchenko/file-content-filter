package com.kravchenko.contentfilter.parser;

import com.kravchenko.contentfilter.model.DataType;
import com.kravchenko.contentfilter.model.FloatData;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * Парсер для данных с плавающей точкой.
 */
public final class FloatParser implements Parser {

    @Override
    public Optional<DataType> parse(final String line) {
        try {
            return Optional.of(new FloatData(new BigDecimal(line.trim())));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
