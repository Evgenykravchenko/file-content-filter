package com.kravchenko.contentfilter.parser;

import com.kravchenko.contentfilter.model.DataType;
import com.kravchenko.contentfilter.model.FloatData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты парсера чисел с плавающей точкой")
class FloatParserTest {

    private final FloatParser parser = new FloatParser();

    @Test
    @DisplayName("Должен успешно парсить обычное дробное число")
    void shouldParseValidFloat() {
        String input = "123.456";

        Optional<DataType> result = parser.parse(input);

        assertTrue(result.isPresent());
        assertInstanceOf(FloatData.class, result.get());
        assertEquals(new BigDecimal("123.456"), ((FloatData) result.get()).value());
    }

    @Test
    @DisplayName("Должен успешно парсить число в экспоненциальной записи")
    void shouldParseExponentialFloat() {
        String input = "1.528535047E-25";

        Optional<DataType> result = parser.parse(input);

        assertTrue(result.isPresent());
        assertEquals(new BigDecimal("1.528535047E-25"), ((FloatData) result.get()).value());
    }

    @Test
    @DisplayName("Должен возвращать empty для нечисловой строки")
    void shouldReturnEmptyForString() {
        String input = "not a number";

        Optional<DataType> result = parser.parse(input);

        assertTrue(result.isEmpty());
    }
}
