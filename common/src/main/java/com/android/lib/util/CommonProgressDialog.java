package com.android.lib.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.android.common.R;

/**
 * date: 2019/1/30
 * desc: 请求网络加载提示框
 */
public final class CommonProgressDialog extends Dialog {

    private String mMessage;
    private boolean cancelTouchOutside;
    private boolean cancelable;

    public CommonProgressDialog(Builder builder) {
        super(builder.mContext);
        mMessage = builder.mMessage;
        cancelTouchOutside = builder.cancelTouchOutside;
        cancelable = builder.cancelable;
    }

    public CommonProgressDialog(Builder builder, int theme) {
        super(builder.mContext, theme);
        mMessage = builder.mMessage;
        cancelTouchOutside = builder.cancelTouchOutside;
        cancelable = builder.cancelable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_progress_layout);
        setCancelable(cancelable);
        setCanceledOnTouchOutside(cancelTouchOutside);
        TextView textView = (TextView) findViewById(R.id.tv_load_dialog);
        if (!TextUtils.isEmpty(mMessage)) {
            textView.setText(mMessage);
        }
//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        getWindow().setAttributes(params);
    }

    public static final class Builder {
        Context mContext;
        private String mMessage;
        private int resStyle = -1;
        private boolean cancelTouchOutside;
        private boolean cancelable;

        public Builder(Context context) {
            this.mContext = context;
        }

        /**
         * 设置主题
         *
         * @param resStyle style id
         * @return ProgressDialog.Builder
         */
        public Builder setTheme(int resStyle) {
            this.resStyle = resStyle;
            return this;
        }

        /**
         * 设置提示信息
         *
         * @param message 提示信息
         * @return builder
         */
        public Builder setMessage(String message) {
            this.mMessage = message;
            return this;
        }

        /**
         * 是否可以取消
         *
         * @param cancelable true&false
         * @return builder
         */
        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        /**
         * 设置点击dialog外部是否取消dialog
         *
         * @param cancelTouchOutside 点击外部是否取消dialog
         * @return builder
         */
        public Builder cancelTouchOutside(boolean cancelTouchOutside) {
            this.cancelTouchOutside = cancelTouchOutside;
            return this;
        }

        public CommonProgressDialog build() {
            if (resStyle != -1) {
                return new CommonProgressDialog(this, resStyle);
            } else {
                return new CommonProgressDialog(this);
            }
        }

    }

}
