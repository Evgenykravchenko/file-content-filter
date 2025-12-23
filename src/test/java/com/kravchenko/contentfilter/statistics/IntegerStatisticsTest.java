package com.kravchenko.contentfilter.statistics;

import com.kravchenko.contentfilter.view.UserOutput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты статистики целых чисел")
class IntegerStatisticsTest {

    private final IntegerStatistics stats = new IntegerStatistics();

    @Mock
    private UserOutput output;

    @Test
    @DisplayName("Должен корректно обновлять статистику")
    void shouldUpdateStatistics() {
        stats.update(BigInteger.valueOf(10));
        stats.update(BigInteger.valueOf(20));
        stats.update(BigInteger.valueOf(5));

        stats.printReport(true, output);

        verify(output).printMessage(contains("Count: 3"));
        verify(output).printMessage(contains("Min: 5"));
        verify(output).printMessage(contains("Max: 20"));
        verify(output).printMessage(contains("Sum: 35"));
        verify(output).printMessage(contains("Avg: 11.666"));
    }

    @Test
    @DisplayName("Должен выводить только краткую статистику если full=false")
    void shouldPrintShortReport() {
        stats.update(BigInteger.valueOf(10));

        stats.printReport(false, output);

        verify(output).printMessage(contains("Count: 1"));
    }
}
