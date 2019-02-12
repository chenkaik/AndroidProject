package com.android.lib.util;

import android.accounts.NetworkErrorException;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

/**
 * @date: 2019/1/30
 * @describe: 处理接口调用失败
 */
public class NetErrStringUtils {

    public static final int ERR_404 = 404;

    public static final int ERR_500 = 500;

    public static final int ERR_502 = 502;

    public static String getErrString(int code) {
        String result;
        switch (code) {
            case ERR_404:
                result = "无法找到指定位置的资源";
                break;
            case ERR_500:
                result = "内部服务器错误";
                break;
            case ERR_502:
                result = "网关错误";
                break;
            default:
                result = "网络错误";
                break;
        }
        return result;
    }

    public static String getErrString(Throwable t) {
        String result;
        if (t instanceof java.net.SocketTimeoutException
                || t instanceof ConnectException
                || t instanceof TimeoutException
                || t instanceof NetworkErrorException
                || t instanceof UnknownHostException) {
            result = "网络连接超时";
        } else if (t != null && t.getMessage() != null && t.getMessage().equalsIgnoreCase("canceled")) {
            result = "";
        } else {
            result = "网络错误";
        }
        return result;
    }

}
