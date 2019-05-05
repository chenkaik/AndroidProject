package com.android.lib.util;

import android.app.Activity;
import android.view.View;

import com.android.common.R;

/**
 * date: 2019/1/30
 * desc: 弹框辅助类
 */
public class CommonHelp {

    private CommonProgressDialog commonProgressDialog;
    private Activity activity;

    public CommonHelp(Activity activity) {
        this.activity = activity;
    }

    /**
     * 显示CommonProgressDialog
     */
    public void showProgress(String msg) {
        isFinish();
        if (commonProgressDialog == null) {
            commonProgressDialog = new CommonProgressDialog.Builder(activity)
                    .setTheme(R.style.commonProgressDialog)
                    .setCancelable(false)
                    .cancelTouchOutside(false)
                    .setMessage(msg)
                    .build();
            commonProgressDialog.show();
        } else {
            if (commonProgressDialog.isShowing()) {
                commonProgressDialog.dismiss();
            } else {
                commonProgressDialog.show();
            }
        }
    }

    /**
     * 取消CommonProgressDialog
     */
    public void dismissProgress() {
        if (commonProgressDialog != null) {
            commonProgressDialog.dismiss();
            commonProgressDialog = null;
//            if (commonProgressDialog.isShowing()) {
//                commonProgressDialog.dismiss();
//                commonProgressDialog = null;
//            } else {
//                commonProgressDialog = null;
//            }
        }
    }

    /**
     * 公用的弹框
     *
     * @param title      标题
     * @param message    内容
     * @param okListener 确定按钮的监听
     * @param noListener 取消按钮的监听
     */
    public void showCommonAlertDialog(String title, String message, final View.OnClickListener okListener, final View.OnClickListener noListener) {
        isFinish();
        new CommonAlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != okListener) {
                            okListener.onClick(v);
                        }
                    }
                })
                .setNegativeButton(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != noListener) {
                            noListener.onClick(v);
                        }
                    }
                })
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .show();
    }

    /**
     * 公用的单个按钮弹框
     *
     * @param title      标题
     * @param message    内容
     * @param okListener 确定按钮的监听
     */
    public void showCommonAlertDialog(String title, String message, final View.OnClickListener okListener) {
        isFinish();
        new CommonAlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setShowOneButton(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != okListener) {
                            okListener.onClick(v);
                        }
                    }
                })
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .show();
    }


    public void isFinish() {
        if (activity == null || activity.isFinishing()) {
            return;
        }
    }

}
