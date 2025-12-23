package com.kravchenko.contentfilter.statistics;

import com.kravchenko.contentfilter.view.UserOutput;

/**
 * Интерфейс для сбора и вывода статистики.
 */
public interface Statistic {

    /**
     * Выводит собранную статистику.
     *
     * @param full   если true, выводит полную статистику
     * @param output обработчик вывода
     */
    void printReport(boolean full, UserOutput output);
}
