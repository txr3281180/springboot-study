package com.txr.spbbasic.global.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberTools {

    /*  整数
    保留两位小数
    保留四位小数
    0-2位小数
    2-4位小数
    0-4位小数
    0-2位小数，一般为整数
    0-4位小数，单位亿元  */

    private static final String NO_VALUE = "--";

    /** 精确指定位数，无单位输入null */
    public static String formatter(String val, int accuracy, String unit) {
        if (unit == null || unit.isEmpty()){
            unit = "";
        }

        BigDecimal bigDecimal = checkValue(val, accuracy);

        return bigDecimal != null ? bigDecimal.toString() + unit : NO_VALUE;
    }

    /** 精确 0 - 2 位 */
    public static String precisionZeroToTwo(String val) {
        BigDecimal bigDecimal = checkValue(val, 2);
        return bigDecimal != null ? removeInvalidNum(bigDecimal.toString(), 0) : NO_VALUE;
    }

    /** 精确 0 - 4 位*/
    public static String precisionZeroToFour(String val) {
        BigDecimal bigDecimal = checkValue(val, 4);
        return bigDecimal != null ? removeInvalidNum(bigDecimal.toString(), 0) : NO_VALUE;
    }

    /**精确 2 - 4 位*/
    public static String precisionTwoToFour(String val) {
        BigDecimal bigDecimal = checkValue(val, 4);
        return bigDecimal != null ? removeInvalidNum(bigDecimal.toString(), 2) : NO_VALUE;
    }

    /** 去掉末尾无效小数 (4.10 --> 4.1)*/
    public static String removeInvalidNum(String val, int accuracy) {
        StringBuilder sbu = new StringBuilder(val);
        for (int i = val.length() - 1; i >= 0; --i) {
            if (val.charAt(i) != '0' || (sbu.length() - 1) - sbu.indexOf(".") <= accuracy) {
                break;
            }
            sbu.deleteCharAt(i);
        }

        if (sbu.length() - 1 == sbu.indexOf(".")) {
            sbu.deleteCharAt(sbu.indexOf("."));
        }
        return sbu.toString();
    }

    /**校验是否为可转数值的字符串*/
    public static BigDecimal checkValue(String val, int accuracy) {
        if (val != null && !val.trim().isEmpty()) {
            try {
                return new BigDecimal(val).setScale(accuracy, RoundingMode.HALF_UP);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }
}
