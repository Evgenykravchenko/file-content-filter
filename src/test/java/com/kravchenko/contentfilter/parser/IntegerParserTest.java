package com.kravchenko.contentfilter.parser;

import com.kravchenko.contentfilter.model.DataType;
import com.kravchenko.contentfilter.model.IntegerData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты парсера целых чисел")
class IntegerParserTest {

    private final IntegerParser parser = new IntegerParser();

    @Test
    @DisplayName("Должен успешно парсить обычное целое число")
    void shouldParseValidInteger() {
        String input = "12345";

        Optional<DataType> result = parser.parse(input);

        assertTrue(result.isPresent());
        assertInstanceOf(IntegerData.class, result.get());
        assertEquals(new BigInteger("12345"), ((IntegerData) result.get()).value());
    }

    @Test
    @DisplayName("Должен успешно парсить очень большое целое число")
    void shouldParseBigInteger() {
        String input = "123456789012345678901234567890";

        Optional<DataType> result = parser.parse(input);

        assertTrue(result.isPresent());
        assertEquals(new BigInteger("123456789012345678901234567890"), ((IntegerData) result.get()).value());
    }

    @Test
    @DisplayName("Должен возвращать empty для дробного числа")
    void shouldReturnEmptyForFloat() {
        String input = "123.45";

        Optional<DataType> result = parser.parse(input);

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Должен возвращать empty для строки")
    void shouldReturnEmptyForString() {
        String input = "not a number";

        Optional<DataType> result = parser.parse(input);

        assertTrue(result.isEmpty());
    }
}
