package com.kravchenko.contentfilter.parser;

import com.kravchenko.contentfilter.model.DataType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты процессора ввода")
class InputProcessorTest {

    @Mock
    private Parser parser1;

    @Mock
    private Parser parser2;

    @Mock
    private DataType mockData;

    private InputProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new InputProcessor(List.of(parser1, parser2));
    }

    @Test
    @DisplayName("Должен возвращать результат первого успешного парсера")
    void shouldReturnFirstSuccessfulParseResult() {
        String input = "test";
        when(parser1.parse(input)).thenReturn(Optional.of(mockData));

        DataType result = processor.process(input);

        assertEquals(mockData, result);
        verify(parser1).parse(input);
        verify(parser2, never()).parse(anyString());
    }

    @Test
    @DisplayName("Должен пробовать следующий парсер, если первый вернул empty")
    void shouldTryNextParserIfFirstFails() {
        String input = "test";
        when(parser1.parse(input)).thenReturn(Optional.empty());
        when(parser2.parse(input)).thenReturn(Optional.of(mockData));

        DataType result = processor.process(input);

        assertEquals(mockData, result);
        verify(parser1).parse(input);
        verify(parser2).parse(input);
    }

    @Test
    @DisplayName("Должен выбрасывать исключение, если ни один парсер не справился")
    void shouldThrowExceptionIfNoParserSucceeds() {
        String input = "unknown";
        when(parser1.parse(input)).thenReturn(Optional.empty());
        when(parser2.parse(input)).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () -> processor.process(input));
    }
}
