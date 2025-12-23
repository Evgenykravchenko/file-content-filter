package com.kravchenko.contentfilter.statistics;

import com.kravchenko.contentfilter.view.UserOutput;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/**
 * Сборщик статистики для целочисленных данных.
 */
public final class IntegerStatistics implements Statistic {

    /**
     * Количество обработанных чисел.
     */
    private long count = 0;

    /**
     * Минимальное найденное значение.
     */
    private BigInteger min = null;

    /**
     * Максимальное найденное значение.
     */
    private BigInteger max = null;

    /**
     * Сумма всех значений.
     */
    private BigInteger sum = BigInteger.ZERO;

    /**
     * Обновляет статистику новым значением.
     *
     * @param value новое целочисленное значение
     */
    public void update(final BigInteger value) {
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

        output.printMessage("Integers:");
        output.printMessage("  Count: " + count);

        if (full) {
            output.printMessage("  Min: " + min);
            output.printMessage("  Max: " + max);
            output.printMessage("  Sum: " + sum);
            output.printMessage("  Avg: "
                    + new BigDecimal(sum).divide(BigDecimal.valueOf(count),
                            MathContext.DECIMAL128));
        }
    }
}
