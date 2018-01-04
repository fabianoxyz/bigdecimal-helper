package xyz.fabiano.bigdecimal.helper;

import org.junit.Before;
import org.junit.Test;
import xyz.fabiano.bigdecimal.helper.utils.BigDecimalOperations;

import java.math.BigDecimal;
import java.math.BigInteger;

import static xyz.fabiano.bigdecimal.helper.utils.BigDecimalOperations.*;
import static xyz.fabiano.bigdecimal.helper.BigDecimalGlobalSettings.*;
import static org.junit.Assert.*;


public class BigDecimalOperationsTest {

    @Before
    public void setupLargeScale() {
        BigDecimalGlobalSettings.defaultScale = 40;
    }

    @Test
    public void testLongAsBigDecimal() {
        BigDecimal aLong = asBigDecimal(100_000_000_000L);
        assertEquals(expectedBigDecimal(BigDecimal.valueOf(100_000_000_000L)), aLong);
    }
    @Test
    public void testIntegerAsBigDecimal() {
        BigDecimal anInteger = asBigDecimal(100_000_000);
        assertEquals(expectedBigDecimal(BigDecimal.valueOf(100_000_000)), anInteger);
    }
    @Test
    public void testFloatAsBigDecimal() {
        BigDecimal aFloat = asBigDecimal(123123.123123F);
        assertEquals(expectedBigDecimal(BigDecimal.valueOf(123123.123123F)), aFloat);
    }

    @Test
    public void testDoubleAsBigDecimal() {
        BigDecimal aDouble = asBigDecimal(432542.1231231D);
        assertEquals(expectedBigDecimal(BigDecimal.valueOf(432542.1231231D)), aDouble);
    }

    @Test
    public void testStringAsBigDecimal() {
        BigDecimal aString = asBigDecimal("10000.32426321234526576723");
        assertEquals(expectedBigDecimal(new BigDecimal("10000.32426321234526576723")), aString);
    }

    @Test
    public void testBigIntegerAsBigDecimal() {
        BigDecimal aBigInteger = asBigDecimal(new BigInteger("12345678910111213141516171819202122232425"));
        assertEquals(expectedBigDecimal(new BigDecimal(new BigInteger("12345678910111213141516171819202122232425"))), aBigInteger);
    }

    @Test
    public void testBigDecimalAsBigDecimal() {
        BigDecimal aBigDecimal = asBigDecimal(new BigDecimal("123123123123.456456456456456456456456"));
        assertEquals(expectedBigDecimal(new BigDecimal("123123123123.456456456456456456456456")), aBigDecimal);
    }

    private BigDecimal expectedBigDecimal(BigDecimal value) {
        return value.setScale(defaultScale, defaultRoundMode);
    }

    @Test
    public void divideBigDecimals() {
        BigDecimal quotient = BigDecimalOperations.divide(BigDecimal.ONE, asBigDecimal(1_000_000_000));
        assertEquals(asBigDecimal(0.000_000_001), quotient);

        quotient = BigDecimalOperations.divide(BigDecimal.ONE, asBigDecimal(400_000_000));
        assertEquals(asBigDecimal(0.000_000_002_5), quotient);

        quotient = BigDecimalOperations.divide(BigDecimal.ZERO, asBigDecimal(400_000_000));
        assertEquals(asBigDecimal(0), quotient);
    }

    @Test
    public void divideIntegers() {
        BigDecimal quotient = BigDecimalOperations.divide(1, 3);
        assertEquals(asBigDecimal("0.33333333333333333333333333333333333333333333333"), quotient);

        quotient = BigDecimalOperations.divide(1, 10);
        assertEquals(asBigDecimal(0.1), quotient);

        quotient = BigDecimalOperations.divide(0, 12);
        assertEquals(asBigDecimal(0), quotient);
    }

    @Test
    public void divideBoth() {
        BigDecimal quotient = BigDecimalOperations.divide(BigDecimal.ONE, 3);
        assertEquals(asBigDecimal("0.33333333333333333333333333333333333333333333333"), quotient);

        quotient = BigDecimalOperations.divide(BigDecimal.ONE, 20);
        assertEquals(asBigDecimal(0.05), quotient);

        quotient = BigDecimalOperations.divide(BigDecimal.ZERO, 34);
        assertEquals(asBigDecimal(0), quotient);
    }

    @Test
    public void sqrt() {
        BigDecimal sqrt = BigDecimalOperations.sqrt(BigDecimal.valueOf(9));
        assertEquals(asBigDecimal(3), sqrt);

        sqrt = BigDecimalOperations.sqrt(asBigDecimal(64));
        assertEquals(asBigDecimal(8), sqrt);

        sqrt = BigDecimalOperations.sqrt(asBigDecimal(2));
        assertEquals(asBigDecimal(1.4142135623730950488), sqrt);

        sqrt = BigDecimalOperations.sqrt(asBigDecimal(97));
        assertEquals(asBigDecimal(9.84885780179610392793), sqrt);
    }
}