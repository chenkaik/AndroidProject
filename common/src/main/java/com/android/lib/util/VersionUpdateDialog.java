package com.android.lib.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.common.R;

/**
 * date: 2019/1/30
 * desc: 更新apk版本使用的dialog
 */
public class VersionUpdateDialog extends Dialog {

    /**
     * 进度条
     */
    private ProgressBar progressBar;
    /**
     * 当前已下载文件的大小
     */
    private TextView currentFileSize;
    /**
     * 文件的大小
     */
    private TextView fileSize;
    /**
     * 下载的百分比
     */
    private TextView percentage;

    public VersionUpdateDialog(@NonNull Context context) {
        super(context, R.style.apk_update_dialog_style);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.version_update_dialog_layout);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        // 初始化界面控件
        initView();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        fileSize = (TextView) findViewById(R.id.fileSize);
        currentFileSize = (TextView) findViewById(R.id.currentFileSize);
        percentage = (TextView) findViewById(R.id.tv_percentage);
    }

    /**
     * 公用方法
     *
     * @param progress    当前下载的进度
     * @param progressMax 下载文件的大小
     * @param currentSize 当前已下载的文件大小
     * @param fileMax     文件大小
     * @param percentage  下载的百分比
     */
    public void setCommon(int progress, int progressMax, String currentSize, String fileMax, int percentage) {
        progressBar.setProgress(progress);
        progressBar.setMax(progressMax);
        currentFileSize.setText(currentSize);
        fileSize.setText(fileMax);
        this.percentage.setText(String.valueOf(percentage) + "%");
    }

    /**
     * 设置当前下载的进度
     *
     * @param progress 进度
     */
    public void setCurrentProgress(int progress) {
        progressBar.setProgress(progress);
    }

    /**
     * 设置下载文件的大小
     *
     * @param progressMax 大小
     */
    public void setProgressMax(int progressMax) {
        progressBar.setMax(progressMax);
    }

    /**
     * 更新当前下载进度
     *
     * @param fileMax     文件大小
     * @param currentSize 当前下载的文件大小
     */
    public void setCurrentFileSize(String fileMax, String currentSize) {
        fileSize.setText(fileMax);
        currentFileSize.setText(currentSize);
    }

}
