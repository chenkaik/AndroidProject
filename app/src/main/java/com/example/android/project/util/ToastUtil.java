package com.example.android.project.util;

import android.annotation.SuppressLint;
import android.widget.Toast;

import com.example.android.project.CommonApplication;

/**
 * date: 2019/10/11
 * desc: toast的封装
 */
public class ToastUtil {

    private static Toast sToast;

    @SuppressLint("ShowToast")
    public static void showToast(String content) {
        if (sToast == null) {
            sToast = Toast.makeText(CommonApplication.getContext(), content, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(content);
        }
        sToast.show();
    }

}
