package xyz.fabiano.bigdecimal.helper.utils;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

import static xyz.fabiano.bigdecimal.helper.BigDecimalGlobalSettings.*;

public class BigDecimalOperations {

    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor) {
        return dividend.divide(divisor, defaultScale, defaultRoundMode);
    }

    public static BigDecimal divide(BigDecimal dividend, Integer divisor) {
        return divide(dividend, asBigDecimal(divisor));
    }

    public static BigDecimal divide(Integer dividend, Integer divisor) {
        return divide(asBigDecimal(dividend), divisor);
    }

    public static BigDecimal multiply(Integer intFactor, BigDecimal... factors) {
        BigDecimal product = Arrays.stream(factors).reduce(BigDecimal.ONE, BigDecimal::multiply);
        return multiply(product, intFactor);
    }

    public static BigDecimal multiply(BigDecimal factor, Integer... intFactors) {
        Integer product = Arrays.stream(intFactors).reduce(1, (f1, f2) -> f1 * f2);
        return factor.multiply(asBigDecimal(product)).setScale(defaultScale, defaultRoundMode);
    }

    public static BigDecimal sqrt(BigDecimal radicand) {
        Double sqrt = Math.sqrt(radicand.doubleValue());
        return asBigDecimal(sqrt);
    }

    public static BigDecimal asBigDecimal(Number value) {
        return standardBigDecimal(BigDecimal.valueOf(value.doubleValue()));
    }

    public static BigDecimal asBigDecimal(BigInteger value) {
        return standardBigDecimal(new BigDecimal(value));
    }

    public static BigDecimal asBigDecimal(BigDecimal value) {
        return standardBigDecimal(value);
    }

    public static BigDecimal asBigDecimal(CharSequence value) {
        return standardBigDecimal(new BigDecimal(value.toString()));
    }

    public static BigDecimal standardBigDecimal(BigDecimal bigDecimal) {
        return bigDecimal.setScale(defaultScale, defaultRoundMode);
    }
}
