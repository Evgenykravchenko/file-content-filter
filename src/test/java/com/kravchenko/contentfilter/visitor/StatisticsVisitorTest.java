package com.kravchenko.contentfilter.visitor;

import com.kravchenko.contentfilter.model.FloatData;
import com.kravchenko.contentfilter.model.IntegerData;
import com.kravchenko.contentfilter.model.StringData;
import com.kravchenko.contentfilter.view.UserOutput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.mockito.ArgumentMatchers.contains;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты посетителя статистики")
class StatisticsVisitorTest {

    @Mock
    private UserOutput output;

    @Test
    @DisplayName("Должен собирать и выводить статистику")
    void shouldCollectAndPrintStatistics() {
        StatisticsVisitor visitor = new StatisticsVisitor(output);

        visitor.visit(new IntegerData(BigInteger.ONE));
        visitor.visit(new FloatData(BigDecimal.TEN));
        visitor.visit(new StringData("test"));

        visitor.printReport(false);

        InOrder inOrder = Mockito.inOrder(output);
        inOrder.verify(output).printMessage(contains("Integers:"));
        inOrder.verify(output).printMessage(contains("Count: 1"));
        inOrder.verify(output).printMessage(contains("Floats:"));
        inOrder.verify(output).printMessage(contains("Count: 1"));
        inOrder.verify(output).printMessage(contains("Strings:"));
        inOrder.verify(output).printMessage(contains("Count: 1"));
    }
}
