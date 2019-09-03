package com.android.lib.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.android.common.R;

/**
 * date: 2019/1/30
 * desc: 更新apk版本使用的dialog
 */
public final class VersionUpdateDialog extends Dialog {

    /**
     * 进度条
     */
    private ProgressBar mProgressBar;
    /**
     * 当前已下载文件的大小
     */
    private TextView mCurrentFileSize;
    /**
     * 文件的大小
     */
    private TextView mFileSize;
    /**
     * 下载的百分比
     */
    private TextView mPercentage;

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
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mFileSize = (TextView) findViewById(R.id.fileSize);
        mCurrentFileSize = (TextView) findViewById(R.id.currentFileSize);
        mPercentage = (TextView) findViewById(R.id.tv_percentage);
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
        mProgressBar.setProgress(progress);
        mProgressBar.setMax(progressMax);
        mCurrentFileSize.setText(currentSize);
        mFileSize.setText(fileMax);
        this.mPercentage.setText(percentage + "%");
    }

    /**
     * 设置当前下载的进度
     *
     * @param progress 进度
     */
    public void setCurrentProgress(int progress) {
        mProgressBar.setProgress(progress);
    }

    /**
     * 设置下载文件的大小
     *
     * @param progressMax 大小
     */
    public void setProgressMax(int progressMax) {
        mProgressBar.setMax(progressMax);
    }

    /**
     * 更新当前下载进度
     *
     * @param fileMax     文件大小
     * @param currentSize 当前下载的文件大小
     */
    public void setCurrentFileSize(String fileMax, String currentSize) {
        mFileSize.setText(fileMax);
        mCurrentFileSize.setText(currentSize);
    }

}
