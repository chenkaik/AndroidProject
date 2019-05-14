package com.android.lib.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

/**
 * date: 2019/1/30
 * desc: 修改状态栏颜色
 */
public class StatusBarUtil {

    /**
     * 修改状态栏颜色，支持4.4以上版本
     *
     * @param activity act
     * @param colorId color
     */
    public static void setStatusBarColors(Activity activity, int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 使状态栏变色，需要先将状态栏设置为透明
            transparencyBar(activity);
            SystemBarTintManager tintManager = new SystemBarTintManager(activity);
            // 激活状态栏设置
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(colorId);
        }
    }

    /**
     * 修改状态栏为全透明
     *
     * @param activity act
     */
    @TargetApi(19)
    public static void transparencyBar(Activity activity) {
        Window window = activity.getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

}
