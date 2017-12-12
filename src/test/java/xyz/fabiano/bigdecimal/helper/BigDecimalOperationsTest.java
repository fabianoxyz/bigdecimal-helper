package xyz.fabiano.bigdecimal.helper;

import org.junit.Test;
import xyz.fabiano.bigdecimal.helper.utils.BigDecimalOperations;

import java.math.BigDecimal;
import static xyz.fabiano.bigdecimal.helper.utils.BigDecimalOperations.*;
import static org.junit.Assert.*;


public class BigDecimalOperationsTest {

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
        assertEquals(asBigDecimal(0.333_333_333_333), quotient);

        quotient = BigDecimalOperations.divide(1, 10);
        assertEquals(asBigDecimal(0.1), quotient);

        quotient = BigDecimalOperations.divide(0, 12);
        assertEquals(asBigDecimal(0), quotient);
    }

    @Test
    public void divideBoth() {
        BigDecimal quotient = BigDecimalOperations.divide(BigDecimal.ONE, 3);
        assertEquals(asBigDecimal(0.333_333_333_333), quotient);

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
        assertEquals(asBigDecimal(1.4142135624), sqrt);

        sqrt = BigDecimalOperations.sqrt(asBigDecimal(97));
        assertEquals(asBigDecimal(9.8488578018), sqrt);
    }
}