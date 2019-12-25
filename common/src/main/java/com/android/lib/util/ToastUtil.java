package com.android.lib.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

/**
 * date: 2019/10/11
 * desc: toast的封装
 */
public final class ToastUtil {

    private static Toast sToast;

    @SuppressLint("ShowToast")
    public static void showToast(Context context, String content) {
        if (sToast == null) {
            sToast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(content);
        }
        sToast.show();
    }

}
