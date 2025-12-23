package com.kravchenko.contentfilter.model;

import com.kravchenko.contentfilter.visitor.DataVisitor;

import java.math.BigDecimal;

/**
 * Запись, представляющая данные с плавающей точкой.
 *
 * @param value значение с плавающей точкой
 */
public record FloatData(BigDecimal value) implements DataType {

    @Override
    public void accept(final DataVisitor visitor) {
        visitor.visit(this);
    }
}
