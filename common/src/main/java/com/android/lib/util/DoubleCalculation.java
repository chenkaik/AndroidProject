package com.android.lib.util;

import java.math.BigDecimal;

/**
 * date: 2019/1/15
 * desc: double值加减乘除计算
 */
public class DoubleCalculation {

    /**
     * 加法运算
     *
     * @param m1 值1
     * @param m2 值2
     * @return 计算结果
     */
    public static double addDouble(double m1, double m2) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.add(p2).doubleValue();
    }

    /**
     * 减法运算
     *
     * @param m1 值1
     * @param m2 值2
     * @return 计算结果
     */
    public static double subDouble(double m1, double m2) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.subtract(p2).doubleValue();
    }

    /**
     * 乘法运算
     *
     * @param m1 值1
     * @param m2 值2
     * @return 计算结果
     */
    public static double mul(double m1, double m2) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.multiply(p2).doubleValue();
    }


    /**
     * 除法运算
     *
     * @param m1    值1
     * @param m2    值2
     * @param scale 四舍五入 小数点位数
     * @return 计算结果
     */
    public static double div(double m1, double m2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("Parameter error");
        }
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.divide(p2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
