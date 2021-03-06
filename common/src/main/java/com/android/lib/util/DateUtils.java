package com.android.lib.util;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * date: 2019/1/30
 * desc: 日期处理相关
 */
public final class DateUtils {

    private static final String yyyyMMddHHmm = "yyyy-MM-dd HH:mm";

    @SuppressLint("SimpleDateFormat")
    public static String currentTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 格式化对象
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return sdf.format(calendar.getTime());
    }

    @SuppressLint("SimpleDateFormat")
    public static String formatDate(String dateStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = simpleDateFormat.parse(dateStr);
            return simpleDateFormat.format(parse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    public static String formateStringH(String dateStr, String pattren) {
        Date date = parseDate(dateStr, pattren);
        try {
            String str = dateToString(date, DateUtils.yyyyMMddHHmm);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return dateStr;
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static Date parseDate(String dateStr, String type) {
        SimpleDateFormat df = new SimpleDateFormat(type);
        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    @SuppressLint("SimpleDateFormat")
    public static String dateToString(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    @SuppressLint("SimpleDateFormat")
    public static Date stringToDate(String dateStr, String pattern) throws Exception {
        return new SimpleDateFormat(pattern).parse(dateStr);
    }

    @SuppressLint("SimpleDateFormat")
    public static String currentTimeDeatil(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // 格式化对象
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return sdf.format(calendar.getTime());
    }

    @SuppressLint("SimpleDateFormat")
    public static String currentMonth() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM"); // 格式化对象
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return sdf.format(calendar.getTime());
    }

    @SuppressLint("SimpleDateFormat")
    public static String lastMonth(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date d;
        try {
            d = sdf.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            calendar.add(Calendar.MONTH, -1);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @SuppressLint("SimpleDateFormat")
    public static String nextMonth(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date d;
        try {
            d = sdf.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            calendar.add(Calendar.MONTH, +1);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @SuppressLint("SimpleDateFormat")
    public static String lastDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd"); // 格式化对象
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return sdf.format(date);
    }

    /**
     * 前7天
     */
    @SuppressLint("SimpleDateFormat")
    public static String lastSevenDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd"); // 格式化对象
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        date = calendar.getTime();
        return sdf.format(date);
    }

    /**
     * 前14天
     */
    @SuppressLint("SimpleDateFormat")
    public static String lastFourteenDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd"); // 格式化对象
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -14);
        date = calendar.getTime();
        return sdf.format(date);
    }

    /**
     * start
     * 本周开始时间戳 - 以星期一为本周的第一天
     *
     * @return 时间
     */
    public static String getWeekStartTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        cal.add(Calendar.DATE, -day_of_week + 1);
        return simpleDateFormat.format(cal.getTime()) + "";
    }

    /**
     * end
     * 本周结束时间戳 - 以星期一为本周的第一天
     *
     * @return 时间
     */
    public static String getWeekEndTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        cal.add(Calendar.DATE, -day_of_week + 7);
        return simpleDateFormat.format(cal.getTime()) + "";
    }

    /**
     * 本月第一天数据
     */
    @SuppressLint("SimpleDateFormat")
    public static String currentFDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd"); // 格式化对象
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        sdf.format(calendar.getTime());
        return sdf.format(calendar.getTime());
    }

    /**
     * 本月最后天数据
     */
    @SuppressLint("SimpleDateFormat")
    public static String currentLDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd"); // 格式化对象
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        sdf.format(calendar.getTime());
        return sdf.format(calendar.getTime());
    }

    @SuppressLint("SimpleDateFormat")
    public static String currentLDaySchedule() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 格式化对象
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        sdf.format(calendar.getTime());
        return sdf.format(calendar.getTime());
    }

    /**
     * 上个月第一天数据
     */
    @SuppressLint("SimpleDateFormat")
    public static String currentFFday() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        Date theDate = calendar.getTime();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        return df.format(gcLast.getTime());

    }

    /**
     * 上个月最后一天数据
     */
    @SuppressLint("SimpleDateFormat")
    public static String currentLLday() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd"); // 格式化对象
        Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH - 1));
        Date theDate = calendar.getTime();
        return df.format(theDate);
    }

    /**
     * 上个月最后一天数据
     */
    @SuppressLint("SimpleDateFormat")
    public static String currentLLdaySchedule(String date) {
        try {
            String aa = date.toString() + "-01";
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // 格式化对象
            Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
            calendar.setTime(df.parse(aa));
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date theDate = calendar.getTime();
            return df.format(theDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 下一个月最后一天数据
     */
    @SuppressLint("SimpleDateFormat")
    public static String lastLLdaySchedule(String date) {
        try {
            String aa = date.toString() + "-01";
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // 格式化对象
            Calendar calendar = Calendar.getInstance(); // 此时打印它获取的是系统当前时间
            calendar.setTime(df.parse(aa));
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date theDate = calendar.getTime();
            return df.format(theDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取最近三十天的数据
     */
    @SuppressLint("SimpleDateFormat")
    public static String lastThrDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd"); // 格式化对象
        Calendar calendar = Calendar.getInstance(); // 此时打印它获取的是系统当前时间
        // calendar.add(Calendar.MONTH, -1); //
        // if (calendar.get(calendar.DAY_OF_MONTH)==1) {
        //
        // }
        calendar.add(Calendar.DATE, -30);
        Date theDate = calendar.getTime();
        return df.format(theDate);
    }

    public static String[] weekName = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    public static int getMonthDays(int year, int month) {
        if (month > 12) {
            month = 1;
            year += 1;
        } else if (month < 1) {
            month = 12;
            year -= 1;
        }
        int[] arr = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int days = 0;

        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            arr[1] = 29;
        }
        try {
            days = arr[month - 1];
        } catch (Exception e) {
            e.getStackTrace();
        }
        return days;
    }

    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    public static int getCurrentMonthDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static int getWeekDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    public static int getHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }
//    public static CustomDate getNextSunday() {
//
//        Calendar c = Calendar.getInstance();
//        c.add(Calendar.DATE, 7 - getWeekDay()+1);
//        CustomDate date = new CustomDate(c.get(Calendar.YEAR),
//                c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH));
//        return date;
//    }

    public static int[] getWeekSunday(int year, int month, int day, int pervious) {
        int[] time = new int[3];
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.add(Calendar.DAY_OF_MONTH, pervious);
        time[0] = c.get(Calendar.YEAR);
        time[1] = c.get(Calendar.MONTH) + 1;
        time[2] = c.get(Calendar.DAY_OF_MONTH);
        return time;
    }

    public static int getWeekDayFromDate(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDateFromString(year, month));
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return week_index;
    }

    @SuppressLint("SimpleDateFormat")
    public static Date getDateFromString(int year, int month) {
        String dateString = year + "-" + (month > 9 ? month : ("0" + month)) + "-01";
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return date;
    }
//    public static boolean isToday(CustomDate date){
//        return(date.year == DateUtils.getYear() &&
//                date.month == DateUtils.getMonth()
//                && date.day == DateUtils.getCurrentMonthDay());
//    }
//
//    public static boolean isCurrentMonth(CustomDate date){
//        return(date.year == DateUtils.getYear() &&
//                date.month == DateUtils.getMonth());
//    }

    /**
     * 获取做给定年月的最后一天
     *
     * @param year  年份
     * @param month 月份
     * @return 天数
     */
    @SuppressLint("SimpleDateFormat")
    public static String getMonthEnd(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        // 设置时间,当前时间不用设置
        // calendar.setTime(new Date());
        // 设置日期为本月最大日期
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        // 打印
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format.format(calendar.getTime()));
        return format.format(calendar.getTime());
    }

    /**
     * 获取做给定年月的第一天
     * Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH) + 1
     *
     * @param year  年份
     * @param month 月份
     * @return 天数
     */
    @SuppressLint("SimpleDateFormat")
    public static String getMonthStart(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        // 设置时间,当前时间不用设置
        // calendar.setTime(new Date());
        // 设置日期为本月最大日期
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DATE, 1);
        // 打印
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(format.format(calendar.getTime()));
        return format.format(calendar.getTime());
    }

    /**
     * 昨天
     */
    @SuppressLint("SimpleDateFormat")
    public static String currentYesterday() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 格式化对象
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        calendar.setTime(date);
        return sdf.format(calendar.getTime());
    }

    /**
     * 本周的第一天
     */
    @SuppressLint("SimpleDateFormat")
    public static String currentWeekone() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 格式化对象
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (calendar.get(Calendar.DAY_OF_WEEK) == 0) {
            calendar.add(Calendar.DATE, -6);
        } else {
            calendar.add(Calendar.DATE, 2 - calendar.get(Calendar.DAY_OF_WEEK));
        }
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取当前的时分
     */
    @SuppressLint("SimpleDateFormat")
    public static String currentHourMinute() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); // 格式化对象
        return sdf.format(date);
    }

    /**
     * 根据提供的年月日获取该月份的第一天
     */
    public static String getSupportBeginDayofMonth(Date date) {
        date.getTime();
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(date);
        startDate.set(Calendar.DAY_OF_MONTH, 1);
        startDate.set(Calendar.HOUR_OF_DAY, 0);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);
        startDate.set(Calendar.MILLISECOND, 0);
        Date firstDate = startDate.getTime();
        return (firstDate.getTime() + "").substring(0, 10);
    }

    /**
     * 根据提供的年月获取该月份的最后一天
     */
    public static String getSupportEndDayofMonth(Date date) {
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(date);
        startDate.set(Calendar.DAY_OF_MONTH, startDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        startDate.set(Calendar.HOUR_OF_DAY, 23);
        startDate.set(Calendar.MINUTE, 59);
        startDate.set(Calendar.SECOND, 59);
        startDate.set(Calendar.MILLISECOND, 999);
        Date firstDate = startDate.getTime();
        return (firstDate.getTime() + "").substring(0, 10);
    }

    /**
     * 比较两个日期的大小，日期格式为yyyy-MM-dd
     * 返回为true第一个日期大于或等于第二个日期
     *
     * @param startDate the first date
     * @param endDate   the second date
     * @return true <br/>false
     */
    @SuppressLint("SimpleDateFormat")
    public static boolean isDateOneBigger(String startDate, String endDate) {
        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1;
        Date dt2;
        try {
            dt1 = sdf.parse(startDate);
            dt2 = sdf.parse(endDate);
            if (dt1.getTime() > dt2.getTime()) {
                isBigger = true;
            } else if (dt1.getTime() == dt2.getTime()) {
                isBigger = true;
            } else if (dt1.getTime() < dt2.getTime()) {
                isBigger = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isBigger;
    }

    /**
     * 比较两个日期的大小，日期格式为yyyy-MM-dd
     * 返回为true第二个日期大于或等于第一个日期
     *
     * @param startDate the first date
     * @param endDate   the second date
     * @return true <br/>false
     */
    @SuppressLint("SimpleDateFormat")
    public static boolean isDateTwoBigger(String startDate, String endDate) {
        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1;
        Date dt2;
        try {
            dt1 = sdf.parse(startDate);
            dt2 = sdf.parse(endDate);
            if (dt1.getTime() > dt2.getTime()) {
                isBigger = false;
            } else if (dt1.getTime() <= dt2.getTime()) {
                isBigger = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isBigger;
    }

    /**
     * 时间戳转换成字符串
     *
     * @param milSecond 时间戳
     * @param pattern   日期格式
     * @return 时间
     */
    @SuppressLint("SimpleDateFormat")
    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 字符串转为时间戳
     *
     * @param dateString 时间
     * @param pattern    格式
     * @return 时间戳
     */
    @SuppressLint("SimpleDateFormat")
    public static long getStringToDate(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 毫秒换算成时间
     *
     * @param mss 毫秒数
     * @return 时间
     */
    public static String formatDateTimes(long mss) {
//        String dateTimes = "";
//        long days = mss / (60 * 60 * 24);
//        long hours = (mss % (60 * 60 * 24)) / (60 * 60);
//        long minutes = (mss % (60 * 60)) / 60;
//        long seconds = mss % 60;
//        if (days > 0) {
////            dateTimes = days + "天" + hours + "小时" + minutes + "分钟" + seconds + "秒";
//            long d = (days * 24) + hours;
//            if (minutes > 0) {
//                dateTimes = d + "h" + minutes + "min";
//            } else {
//                dateTimes = d + "h";
//            }
//        } else if (hours > 0) {
//            if (minutes > 0) {
//                dateTimes = hours + "h" + minutes + "min";
//            } else {
//                dateTimes = hours + "h";
//            }
//        } else if (minutes > 0) {
//            if (seconds >= 30) {
//                dateTimes = (minutes + 1) + "min";
//            } else {
//                dateTimes = minutes + "min";
//            }
//        } else {
//            if (seconds >= 30) {
//                dateTimes = 1 + "min";
//            } else {
//                dateTimes = "0";
//            }
//        }
//        return dateTimes;

        String dateTimes = "";
        long days = mss / (60 * 60 * 24);
        long hours = (mss % (60 * 60 * 24)) / (60 * 60);
        long minutes = (mss % (60 * 60)) / 60;
        long seconds = mss % 60;
        if (days > 0) {
            dateTimes = days + "天" + hours + "小时" + minutes + "分钟" + seconds + "秒";
        } else if (hours > 0) {
            dateTimes = hours + "小时" + minutes + "分钟" + seconds + "秒";
        } else if (minutes > 0) {
            dateTimes = minutes + "分钟" + seconds + "秒";
        } else {
            dateTimes = seconds + "秒";
        }
        return dateTimes;
    }

    /**
     * 根据时间戳计算时间
     * 计算time2减去time1的差值 差值只设置 几天 几个小时 或 几分钟
     * 根据差值返回多长之间前或多长时间后
     *
     * @param time1 开始时间戳
     * @param time2 结束时间戳
     * @return 几天 几个小时 或 几分钟
     */
    public static String getDistanceTime(long time1, long time2) {
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        long diff;

        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if (day != 0) {
            return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
        }
        if (hour != 0) {
            return hour + "小时" + min + "分钟" + sec + "秒";
        }
        if (min != 0) {
            return min + "分钟" + sec + "秒";
        }
        if (sec != 0) {
            return sec + "秒";
        }
        return "0秒";
    }

}
