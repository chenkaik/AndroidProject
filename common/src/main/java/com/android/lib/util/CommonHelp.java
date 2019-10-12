package com.android.lib.util;

import android.app.Activity;
import android.view.View;

import com.android.common.R;

/**
 * date: 2019/1/30
 * desc: 弹框辅助类
 */
public final class CommonHelp {

    private CommonProgressDialog mCommonProgressDialog;
    private Activity mActivity;

    public CommonHelp(Activity activity) {
        this.mActivity = activity;
    }

    /**
     * 显示CommonProgressDialog
     */
    public void showProgress(String msg) {
        if (mCommonProgressDialog == null) {
            mCommonProgressDialog = new CommonProgressDialog.Builder(mActivity)
                    .setTheme(R.style.commonProgressDialog)
                    .setCancelable(false)
                    .cancelTouchOutside(false)
                    .setMessage(msg)
                    .build();
            mCommonProgressDialog.show();
        } else {
            if (mCommonProgressDialog.isShowing()) {
                mCommonProgressDialog.dismiss();
            } else {
                mCommonProgressDialog.show();
            }
        }
    }

    /**
     * 取消CommonProgressDialog
     */
    public void dismissProgress() {
        if (mCommonProgressDialog != null) {
            mCommonProgressDialog.dismiss();
            mCommonProgressDialog = null;
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
        new CommonAlertDialog.Builder(mActivity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(v -> {
                    if (null != okListener) {
                        okListener.onClick(v);
                    }
                })
                .setNegativeButton(v -> {
                    if (null != noListener) {
                        noListener.onClick(v);
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
        new CommonAlertDialog.Builder(mActivity)
                .setTitle(title)
                .setMessage(message)
                .setShowOneButton(v -> {
                    if (null != okListener) {
                        okListener.onClick(v);
                    }
                })
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .show();
    }

}
