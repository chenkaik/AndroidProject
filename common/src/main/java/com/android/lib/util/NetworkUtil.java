package com.android.lib.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

import com.android.lib.Logger;

/**
 * date: 2019/1/30
 * desc: 判断是否有网络
 */
public class NetworkUtil {

    private static final String TAG = NetworkUtil.class.getSimpleName();

    /**
     * 判断网络是否可用
     *
     * @param context 上下文
     * @return true & false
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetInfo = cm.getActiveNetworkInfo();
            if (null == activeNetInfo || !activeNetInfo.isAvailable() || !activeNetInfo.isConnected()) {
                Logger.e(TAG, "NetWork is unavailable");
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * 判断wifi 的设置 是否是一直 链接
     *
     * @return true一直链接或者当前网络不是wifi，false当前网络是wifi并且设置不是一直链接
     */
    public static boolean isWifiAlwaysConnect(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null && info.getType() == ConnectivityManager.TYPE_WIFI) {
                int wifiSleepPolicy = Settings.System.getInt(context.getContentResolver(), "wifi_sleep_policy", Settings.System.WIFI_SLEEP_POLICY_DEFAULT);
                if (wifiSleepPolicy != Settings.System.WIFI_SLEEP_POLICY_NEVER) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static String getNetworkName(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo info = cm.getActiveNetworkInfo();
            return (info == null) ? "No network" : info.getTypeName();
        } else {
            return "No network";
        }
    }

    /**
     * 检测当的网络状态
     *
     * @param context Context
     * @return true 表示网络可用
     */
    public static boolean isNetworkAvailables(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

}