package com.kravchenko.contentfilter.visitor;

import com.kravchenko.contentfilter.model.FloatData;
import com.kravchenko.contentfilter.model.IntegerData;
import com.kravchenko.contentfilter.model.StringData;
import com.kravchenko.contentfilter.statistics.FloatStatistics;
import com.kravchenko.contentfilter.statistics.IntegerStatistics;
import com.kravchenko.contentfilter.statistics.StringStatistics;
import com.kravchenko.contentfilter.view.UserOutput;
import lombok.Getter;

/**
 * Посетитель, который собирает статистику по обработанным данным.
 */
@Getter
public final class StatisticsVisitor implements DataVisitor {

    /**
     * Статистика для целых чисел.
     */
    private final IntegerStatistics integerStats = new IntegerStatistics();

    /**
     * Статистика для чисел с плавающей точкой.
     */
    private final FloatStatistics floatStats = new FloatStatistics();

    /**
     * Статистика для строк.
     */
    private final StringStatistics stringStats = new StringStatistics();

    /**
     * Обработчик вывода.
     */
    private final UserOutput output;

    /**
     * Создает новый StatisticsVisitor.
     *
     * @param userOutput обработчик вывода для печати отчетов
     */
    public StatisticsVisitor(final UserOutput userOutput) {
        this.output = userOutput;
    }

    @Override
    public void visit(final IntegerData data) {
        integerStats.update(data.value());
    }

    @Override
    public void visit(final FloatData data) {
        floatStats.update(data.value());
    }

    @Override
    public void visit(final StringData data) {
        stringStats.update(data.value());
    }

    /**
     * Выводит собранную статистику.
     *
     * @param full если true, выводит полную статистику; иначе - краткую
     */
    public void printReport(final boolean full) {
        integerStats.printReport(full, output);
        floatStats.printReport(full, output);
        stringStats.printReport(full, output);
    }
}
