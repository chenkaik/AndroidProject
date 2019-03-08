package com.example.android.project.util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;

/**
 * @date: 2019/3/7
 * @describe: 刘海屏控制
 */
public class DisplayCutoutDemo {
    /**
     * 第一种模式:
     * LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT :全屏窗口不会使用到刘海区域，非全屏窗口可正常使用刘海区域 //  仅仅当系统提供的bar完全包含了刘海区时才允许window扩展到刘海区,否则window不会和刘海区重叠,默认情况下，
     *
     * 第二种模式:
     * LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES :允许window扩展到刘海区(原文说的是短边的刘海区, 目前有刘海的手机都在短边,所以就不纠结了)
     *
     * 第三种模式:
     * LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER: 不允许window扩展到刘海区,窗口不允许和刘海屏重叠
     *
     * 1）设置LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES模式
     *
     * 2）设置沉浸式布局模式
     *
     * 3）计算状态栏高度，进行布局；如果有特殊UI要求，则可以使用DisplayCutoutDemo类去获取刘海屏的坐标，完成UI
     */

    private Activity mAc;

    public DisplayCutoutDemo(Activity ac) {

        mAc = ac;
    }

//在使用LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES的时候，状态栏会显示为白色，这和主内容区域颜色冲突,

    //所以我们要开启沉浸式布局模式，即真正的全屏模式,以实现状态和主体内容背景一致

    public void openFullScreenModel() {

        mAc.requestWindowFeature(Window.FEATURE_NO_TITLE);

        WindowManager.LayoutParams lp = mAc.getWindow().getAttributes();

        lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;

        mAc.getWindow().setAttributes(lp);

        View decorView = mAc.getWindow().getDecorView();

        int systemUiVisibility = decorView.getSystemUiVisibility();

        int flags = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

                | View.SYSTEM_UI_FLAG_FULLSCREEN;

        systemUiVisibility |= flags;

        mAc.getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);

    }

//获取状态栏高度

    public int getStatusBarHeight(Context context) {

        int result = 0;

        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0) {

            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        Log.d("hwj", "**getStatusBarHeight**" + result);

        return result;
    }

    public void controlView() {

        View decorView = mAc.getWindow().getDecorView();

        if (decorView != null) {

            Log.d("hwj", "**controlView**" + android.os.Build.VERSION.SDK_INT);

            Log.d("hwj", "**controlView**" + android.os.Build.VERSION_CODES.P);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                WindowInsets windowInsets = decorView.getRootWindowInsets();
                if (windowInsets != null) {

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {

                        DisplayCutout displayCutout = windowInsets.getDisplayCutout();

                        //getBoundingRects返回List<Rect>,没一个list表示一个不可显示的区域，即刘海屏，可以遍历这个list中的Rect,

                        //即可以获得每一个刘海屏的坐标位置,当然你也可以用类似getSafeInsetBottom的api

                        Log.d("hwj", "**controlView**" + displayCutout.getBoundingRects());

                        Log.d("hwj", "**controlView**" + displayCutout.getSafeInsetBottom());

                        Log.d("hwj", "**controlView**" + displayCutout.getSafeInsetLeft());

                        Log.d("hwj", "**controlView**" + displayCutout.getSafeInsetRight());

                        Log.d("hwj", "**controlView**" + displayCutout.getSafeInsetTop());

                    }
                }
            }

        }
    }


}
