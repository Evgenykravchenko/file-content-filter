package com.kravchenko.contentfilter.statistics;

import com.kravchenko.contentfilter.view.UserOutput;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Сборщик статистики для данных с плавающей точкой.
 */
public final class FloatStatistics implements Statistic {

    /**
     * Количество обработанных чисел.
     */
    private long count = 0;

    /**
     * Минимальное найденное значение.
     */
    private BigDecimal min = null;

    /**
     * Максимальное найденное значение.
     */
    private BigDecimal max = null;

    /**
     * Сумма всех значений.
     */
    private BigDecimal sum = BigDecimal.ZERO;

    /**
     * Обновляет статистику новым значением.
     *
     * @param value новое значение с плавающей точкой
     */
    public void update(final BigDecimal value) {
        count++;

        if (min == null || value.compareTo(min) < 0) {
            min = value;
        }

        if (max == null || value.compareTo(max) > 0) {
            max = value;
        }

        sum = sum.add(value);
    }

    @Override
    public void printReport(final boolean full, final UserOutput output) {
        if (count == 0) {
            return;
        }

        output.printMessage("Floats:");
        output.printMessage("  Count: " + count);

        if (full) {
            output.printMessage("  Min: " + min);
            output.printMessage("  Max: " + max);
            output.printMessage("  Sum: " + sum);
            output.printMessage("  Avg: "
                    + sum.divide(BigDecimal.valueOf(count),
                            MathContext.DECIMAL128));
        }
    }
}
