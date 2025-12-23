package com.kravchenko.contentfilter.parser;

import com.kravchenko.contentfilter.model.DataType;
import com.kravchenko.contentfilter.model.StringData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты парсера строк")
class StringParserTest {

    private final StringParser parser = new StringParser();

    @Test
    @DisplayName("Должен успешно парсить любую строку")
    void shouldParseString() {
        String input = "Any random string 123";

        Optional<DataType> result = parser.parse(input);

        assertTrue(result.isPresent());
        assertInstanceOf(StringData.class, result.get());
        assertEquals(input, ((StringData) result.get()).value());
    }

    @Test
    @DisplayName("Должен возвращать empty для null")
    void shouldReturnEmptyForNull() {
        String input = null;

        Optional<DataType> result = parser.parse(input);

        assertTrue(result.isEmpty());
    }
}
