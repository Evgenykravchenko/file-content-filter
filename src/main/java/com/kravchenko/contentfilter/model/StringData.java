package com.kravchenko.contentfilter.model;

import com.kravchenko.contentfilter.visitor.DataVisitor;

/**
 * Запись, представляющая строковые данные.
 *
 * @param value строковое значение
 */
public record StringData(String value) implements DataType {

    @Override
    public void accept(final DataVisitor visitor) {
        visitor.visit(this);
    }
}
