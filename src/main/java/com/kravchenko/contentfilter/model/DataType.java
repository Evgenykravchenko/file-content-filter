package com.kravchenko.contentfilter.model;

import com.kravchenko.contentfilter.visitor.DataVisitor;

/**
 * Интерфейс, представляющий элемент обработанных данных.
 */
public interface DataType {

    /**
     * Принимает посетителя для обработки этих данных.
     *
     * @param visitor посетитель для приема
     */
    void accept(DataVisitor visitor);
}
