package com.example.cf_sdk.changebankapi.model.account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.DecimalFormat;

/**
 * Money convertor class for $ sign
 */

public class Money extends BigDecimal implements Serializable {

    /**
     * Defined centrally, to allow for easy changes to the rounding mode.
     */
    private static int ROUNDING_MODE = BigDecimal.ROUND_HALF_EVEN;

    /**
     * Number of decimals to retain. Also referred to as "scale".
     */
    private static int DECIMALS = 2;

    public Money(char[] in, int offset, int len) {
        super(in, offset, len);
    }

    public Money(char[] in, int offset, int len, MathContext mc) {
        super(in, offset, len, mc);
    }

    public Money(char[] in) {
        super(in);
    }

    public Money(char[] in, MathContext mc) {
        super(in, mc);
    }

    public Money(String val) {
        super(val);
    }

    public Money(String val, MathContext mc) {
        super(val, mc);
    }

    public Money(double val) {
        super(val);
    }

    public Money(double val, MathContext mc) {
        super(val, mc);
    }

    public Money(BigInteger val) {
        super(val);
    }

    public Money(BigInteger val, MathContext mc) {
        super(val, mc);
    }

    public Money(BigInteger unscaledVal, int scale) {
        super(unscaledVal, scale);
    }

    public Money(BigInteger unscaledVal, int scale, MathContext mc) {
        super(unscaledVal, scale, mc);
    }

    public Money(int val) {
        super(val);
    }

    public Money(int val, MathContext mc) {
        super(val, mc);
    }

    public Money(long val) {
        super(val);
    }

    public Money(long val, MathContext mc) {
        super(val, mc);
    }

    public String printMoneyString() {
        DecimalFormat decimalFormat = new DecimalFormat("##,##0.00");
        decimalFormat.setMaximumFractionDigits(2);
        String formattedMoneyString = decimalFormat.format(scaleByPowerOfTen(-2).doubleValue());

        return String.format("$%s", formattedMoneyString);
    }
}
