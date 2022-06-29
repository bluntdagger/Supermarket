package com.sp.supermarket.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * Utility to round amount to common rounding
 * Bigdecimal is used to round half up
 * @author Waleed Naveed
 * 29/6/22
 */
public class BigDecimalUtil {

    public static BigDecimal getBigDecimal(Double val){
        return new BigDecimal(val).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal getBigDecimal(String s) {
        return new BigDecimal(s).setScale(2, RoundingMode.HALF_UP);
    }
}
