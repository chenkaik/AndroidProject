package com.android.lib.listener;

import java.io.File;

/**
 * Created by chenKai on 2018/6/19.
 * 文件下载的监听
 */
public interface FileDownloadListener {

    /**
     * 获取断点已下载文件的大小
     *
     * @param downloadLength
     * @param contentLength
     */
    void onProgress(long downloadLength, long contentLength);

    /**
     * 当前下载进度
     *
     * @param progress
     * @param contentLength
     * @param percentage
     */
    void onProgressUpdate(int progress, long contentLength, int percentage);

    /**
     * 下载成功
     *
     * @param file
     */
    void onSuccess(File file);

    /**
     * 下载失败
     */
    void onFailed();

}
