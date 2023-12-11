package com.taxes.taxe.util;

import org.apache.commons.math3.util.Precision;
public class Utils {

    private Utils() {
    }

    public static final int SCALE = 2;

    public static Double roundScaleTwoNumbers(Double value){
        return Precision.round(value, SCALE);
    }
}
