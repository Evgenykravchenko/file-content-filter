package com.kravchenko.contentfilter.visitor;

import com.kravchenko.contentfilter.model.FloatData;
import com.kravchenko.contentfilter.model.IntegerData;
import com.kravchenko.contentfilter.model.StringData;
import java.util.ArrayList;
import java.util.List;

/**
 * Посетитель, который делегирует выполнение списку других посетителей.
 */
public final class CompositeVisitor implements DataVisitor {
    /**
     * Список посетителей для делегирования.
     */
    private final List<DataVisitor> visitors;

    /**
     * Создает новый CompositeVisitor.
     *
     * @param visitorsList список посетителей для делегирования
     */
    public CompositeVisitor(final List<DataVisitor> visitorsList) {
        this.visitors = new ArrayList<>(visitorsList);
    }

    @Override
    public void visit(final IntegerData data) {
        visitors.forEach(v -> v.visit(data));
    }

    @Override
    public void visit(final FloatData data) {
        visitors.forEach(v -> v.visit(data));
    }

    @Override
    public void visit(final StringData data) {
        visitors.forEach(v -> v.visit(data));
    }
}
