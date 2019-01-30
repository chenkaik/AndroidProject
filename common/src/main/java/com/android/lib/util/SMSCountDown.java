package com.android.lib.util;

import android.os.Handler;
import android.widget.TextView;

/**
 * @date: 2019/1/30
 * @author: Kai
 * @describe: 倒计时
 */
public class SMSCountDown {

    private TextView textView;
    private long time;
    private Task task;
    private Handler handler = new Handler();
    private boolean isRunning = false;
    private CharSequence originalTitle;
    private long timeoutTime;

    private SMSCountDown(){
    }

    /**
     * 短信倒计数功能（默认总时间为120s）
     */
    public SMSCountDown(TextView textView) {
        this.textView = textView;
        // 120秒
        this.time = 120 * 1000;
        task = new Task();
        originalTitle = textView.getText();
    }

    /**
     * 短信倒计数功能
     *
     * @param textView 要显示的控件
     * @param time     总共时长，单位是毫秒
     */
    public SMSCountDown(TextView textView, long time) {
        this.textView = textView;
        this.time = time;
        this.task = new Task();
        originalTitle = textView.getText();
    }

    /**
     * 开始倒计时
     */
    synchronized public void start() {
        if (isRunning) {
            return;
        }
        isRunning = true;
        textView.setEnabled(false);
        StringBuilder builder = new StringBuilder();
        builder.append(originalTitle).append("(").append(time / 1000).append("s)");
        textView.setText(builder.toString());
        timeoutTime = System.currentTimeMillis() + time;
        handler.postDelayed(task, 1000);
    }

    /**
     * 停止倒计时
     */
    synchronized public void stop() {
        if (!isRunning) {
            return;
        }
        isRunning = false;
        handler.removeCallbacks(task);
        textView.setEnabled(true);
        textView.setText(originalTitle);
    }

    synchronized private boolean updateUi() {
        // 如果已经停止运行，那么下面逻辑不做任何处理
        if (!isRunning) {
            return false;
        }
        long now = System.currentTimeMillis();
        long displayTime = timeoutTime - now;
        if (displayTime < 1000) {
            stop();
            return false;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(originalTitle).append("(").append(displayTime / 1000).append("s)");
        textView.setText(builder.toString());
        return true;
    }

    private class Task implements Runnable {
        @Override
        public void run() {
            if (updateUi()) {
                handler.postDelayed(this, 1000);
            }
        }
    }

}
