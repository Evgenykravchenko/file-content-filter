package com.kravchenko.contentfilter.parser;

import com.kravchenko.contentfilter.model.DataType;
import com.kravchenko.contentfilter.model.FloatData;
import com.kravchenko.contentfilter.model.IntegerData;
import com.kravchenko.contentfilter.model.StringData;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void testIntegerParser() {
        Parser parser = new IntegerParser();

        Optional<DataType> result1 = parser.parse("12345");
        assertTrue(result1.isPresent());
        assertInstanceOf(IntegerData.class, result1.get());
        assertEquals(new BigInteger("12345"), ((IntegerData) result1.get()).value());

        Optional<DataType> result2 = parser.parse("123.45");
        assertFalse(result2.isPresent());

        Optional<DataType> result3 = parser.parse("abc");
        assertFalse(result3.isPresent());
    }

    @Test
    void testFloatParser() {
        Parser parser = new FloatParser();

        Optional<DataType> result1 = parser.parse("123.45");
        assertTrue(result1.isPresent());
        assertInstanceOf(FloatData.class, result1.get());
        assertEquals(new BigDecimal("123.45"), ((FloatData) result1.get()).value());

        Optional<DataType> result2 = parser.parse("-1.5E-10");
        assertTrue(result2.isPresent());

        Optional<DataType> result3 = parser.parse("abc");
        assertFalse(result3.isPresent());
    }

    @Test
    void testStringParser() {
        Parser parser = new StringParser();

        Optional<DataType> result = parser.parse("Any String");
        assertTrue(result.isPresent());
        assertInstanceOf(StringData.class, result.get());
        assertEquals("Any String", ((StringData) result.get()).value());
    }
}
