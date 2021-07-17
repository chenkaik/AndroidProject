package com.android.lib.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * date: 2019/1/15
 * desc: double值加减乘除计算
 */
public final class DoubleCalculation {

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

    /**
     * BigDecimal.ROUND_DOWN:直接省略多余的小数，比如1.28如果保留1位小数，得到的就是1.2
     *
     * BigDecimal.ROUND_UP:直接进位，比如1.21如果保留1位小数，得到的就是1.3
     *
     * BigDecimal.ROUND_HALF_UP:四舍五入，2.35保留1位，变成2.4
     *
     * BigDecimal.ROUND_HALF_DOWN:四舍五入，2.35保留1位，变成2.3
     * 后边两种的区别就是如果保留的位数的后一位如果正好是5的时候，一个舍弃掉，一个进位。
     *
     * setScale(1)表示保留一位小数，默认用四舍五入方式
     * setScale(1,BigDecimal.ROUND_DOWN)直接删除多余的小数位，如2.35会变成2.3
     * setScale(1,BigDecimal.ROUND_UP)进位处理，2.35变成2.4
     * setScale(1,BigDecimal.ROUND_HALF_UP)四舍五入，2.35变成2.4
     * setScale(1,BigDecimal.ROUND_HALF_DOWN)四舍五入，2.35变成2.3，如果是5则向下舍
     */

    /**
     * 分转元，转换为bigDecimal在toString
     *
     * @return
     * @param price
     */
    public static String changeF2Y(double price) {
        return BigDecimal.valueOf(price).divide(new BigDecimal(100),1,BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 元转分，确保price保留两位有效数字
     *
     * @return
     */
    public static int changeY2F(double price) {
        DecimalFormat df = new DecimalFormat("#.00");
        price = Double.valueOf(df.format(price));
        int money = (int) (price * 100);
        return money;
    }

}
