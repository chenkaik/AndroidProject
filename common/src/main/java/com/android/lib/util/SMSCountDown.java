package com.android.lib.util;

import android.os.Handler;
import android.widget.TextView;

/**
 * date: 2019/1/30
 * desc: 倒计时
 */
public class SMSCountDown {

    private TextView mTextView;
    private long mTime;
    private Task mTask;
    private Handler mHandler = new Handler();
    private boolean isRunning = false;
    private CharSequence mOriginalTitle;
    private long mTimeoutTime;

    private SMSCountDown(){
    }

    /**
     * 短信倒计数功能（默认总时间为120s）
     */
    public SMSCountDown(TextView textView) {
        this.mTextView = textView;
        // 120秒
        this.mTime = 120 * 1000;
        mTask = new Task();
        mOriginalTitle = textView.getText();
    }

    /**
     * 短信倒计数功能
     *
     * @param textView 要显示的控件
     * @param time     总共时长，单位是毫秒
     */
    public SMSCountDown(TextView textView, long time) {
        this.mTextView = textView;
        this.mTime = time;
        this.mTask = new Task();
        mOriginalTitle = textView.getText();
    }

    /**
     * 开始倒计时
     */
    synchronized public void start() {
        if (isRunning) {
            return;
        }
        isRunning = true;
        mTextView.setEnabled(false);
        StringBuilder builder = new StringBuilder();
        builder.append(mOriginalTitle).append("(").append(mTime / 1000).append("s)");
        mTextView.setText(builder.toString());
        mTimeoutTime = System.currentTimeMillis() + mTime;
        mHandler.postDelayed(mTask, 1000);
    }

    /**
     * 停止倒计时
     */
    synchronized public void stop() {
        if (!isRunning) {
            return;
        }
        isRunning = false;
        mHandler.removeCallbacks(mTask);
        mTextView.setEnabled(true);
        mTextView.setText(mOriginalTitle);
    }

    synchronized private boolean updateUi() {
        // 如果已经停止运行，那么下面逻辑不做任何处理
        if (!isRunning) {
            return false;
        }
        long now = System.currentTimeMillis();
        long displayTime = mTimeoutTime - now;
        if (displayTime < 1000) {
            stop();
            return false;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(mOriginalTitle).append("(").append(displayTime / 1000).append("s)");
        mTextView.setText(builder.toString());
        return true;
    }

    private class Task implements Runnable {
        @Override
        public void run() {
            if (updateUi()) {
                mHandler.postDelayed(this, 1000);
            }
        }
    }

}
