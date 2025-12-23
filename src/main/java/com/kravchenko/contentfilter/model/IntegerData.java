package com.kravchenko.contentfilter.model;

import com.kravchenko.contentfilter.visitor.DataVisitor;

import java.math.BigInteger;

/**
 * Запись, представляющая целочисленные данные.
 *
 * @param value целочисленное значение
 */
public record IntegerData(BigInteger value) implements DataType {

    @Override
    public void accept(final DataVisitor visitor) {
        visitor.visit(this);
    }
}
