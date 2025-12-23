package com.kravchenko.contentfilter.visitor;

import com.kravchenko.contentfilter.model.FloatData;
import com.kravchenko.contentfilter.model.IntegerData;
import com.kravchenko.contentfilter.model.StringData;

/**
 * Интерфейс посетителя для обработки различных типов данных.
 */
public interface DataVisitor {

    /**
     * Посещает целочисленные данные.
     *
     * @param data целочисленные данные
     */
    void visit(IntegerData data);

    /**
     * Посещает данные с плавающей точкой.
     *
     * @param data данные с плавающей точкой
     */
    void visit(FloatData data);

    /**
     * Посещает строковые данные.
     *
     * @param data строковые данные
     */
    void visit(StringData data);
}
