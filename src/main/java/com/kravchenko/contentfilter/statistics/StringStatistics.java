package com.kravchenko.contentfilter.statistics;

import com.kravchenko.contentfilter.view.UserOutput;

/**
 * Сборщик статистики для строковых данных.
 */
public final class StringStatistics implements Statistic {

    /**
     * Количество обработанных строк.
     */
    private long count = 0;

    /**
     * Минимальная длина строки.
     */
    private long minLen = Long.MAX_VALUE;

    /**
     * Максимальная длина строки.
     */
    private long maxLen = 0;

    /**
     * Обновляет статистику новым значением.
     *
     * @param value новое строковое значение
     */
    public void update(final String value) {
        count++;
        long len = value.length();

        if (len < minLen) {
            minLen = len;
        }

        if (len > maxLen) {
            maxLen = len;
        }
    }

    @Override
    public void printReport(final boolean full, final UserOutput output) {
        if (count == 0) {
            return;
        }

        output.printMessage("Strings:");
        output.printMessage("  Count: " + count);

        if (full) {
            output.printMessage("  Min Length: " + (count > 0 ? minLen : 0));
            output.printMessage("  Max Length: " + maxLen);
        }
    }
}
