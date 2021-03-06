package com.android.lib;

import android.util.Log;

/**
 * @author: chen_kai
 * @date：2019/1/30
 * @desc：统一管理打印日志
 */
public final class Logger {

    /**
     * 设为false关闭日志
     */
    public static boolean LOG_ENABLE = true;

    public static void i(String tag, String msg) {
        if (LOG_ENABLE) {
            Log.i(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (LOG_ENABLE) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LOG_ENABLE) {
            Log.d(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (LOG_ENABLE) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LOG_ENABLE) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (LOG_ENABLE) {
            Log.e(tag, msg, tr);
        }
    }

}
