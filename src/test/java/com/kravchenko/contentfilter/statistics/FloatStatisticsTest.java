package com.kravchenko.contentfilter.statistics;

import com.kravchenko.contentfilter.view.UserOutput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты статистики дробных чисел")
class FloatStatisticsTest {

    private final FloatStatistics stats = new FloatStatistics();

    @Mock
    private UserOutput output;

    @Test
    @DisplayName("Должен корректно обновлять статистику")
    void shouldUpdateStatistics() {
        stats.update(new BigDecimal("10.5"));
        stats.update(new BigDecimal("20.1"));
        stats.update(new BigDecimal("5.2"));

        stats.printReport(true, output);

        verify(output).printMessage(contains("Count: 3"));
        verify(output).printMessage(contains("Min: 5.2"));
        verify(output).printMessage(contains("Max: 20.1"));
        verify(output).printMessage(contains("Sum: 35.8"));
    }
}
