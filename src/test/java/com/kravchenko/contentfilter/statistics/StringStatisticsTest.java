package com.kravchenko.contentfilter.statistics;

import com.kravchenko.contentfilter.view.UserOutput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты статистики строк")
class StringStatisticsTest {

    private final StringStatistics stats = new StringStatistics();

    @Mock
    private UserOutput output;

    @Test
    @DisplayName("Должен корректно обновлять статистику")
    void shouldUpdateStatistics() {
        stats.update("short");
        stats.update("longer string");
        stats.update("a");

        stats.printReport(true, output);

        verify(output).printMessage(contains("Count: 3"));
        verify(output).printMessage(contains("Min Length: 1"));
        verify(output).printMessage(contains("Max Length: 13"));
    }
}
