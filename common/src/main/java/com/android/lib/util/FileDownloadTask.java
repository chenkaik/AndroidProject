package com.android.lib.util;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.android.lib.listener.FileDownloadListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * date: 2019/1/30
 * desc: 文件下载
 */
public class FileDownloadTask extends AsyncTask<String, Integer, Integer> {

    public static final int SUCCESS = 0;
    public static final int FAILED = 1;
    private FileDownloadListener listener; // 下载监听
    private int lastProgress;
    private InputStream is = null;
    private RandomAccessFile saveFile = null;
    private File file = null;
    private File downloadDir; // 下载文件存放的目录
    private long contentLength; // 文件大小

    public FileDownloadTask(FileDownloadListener listener, File downloadDir) {
        this.listener = listener;
        this.downloadDir = downloadDir;
    }

    @Override
    protected Integer doInBackground(String... params) {
        try {
            long downloadLength = 0; // 记录已下载文件的长度
            String downloadUrl = params[0];
            file = new File(downloadDir.getPath() + "/version_Update.apk");
            if (file.exists()) {
                file.delete();
            }
            contentLength = getFileLength(downloadUrl); // 得到下载文件大小
            if (contentLength == 0) { // 当下载文件的长度为0时处理
                return FAILED;
            } else if (contentLength == downloadLength) { // 已下载的字节和文件的总字节相等，说明已经下载完成
                return SUCCESS;
            }
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .addHeader("RANGE", "bytes=" + downloadLength + "-") // 指定从哪个字节开始下载
                    .url(downloadUrl)
                    .build();
            Response response = client.newCall(request).execute();
            if (response != null) {
                is = response.body().byteStream();
                saveFile = new RandomAccessFile(file, "rw");
//                saveFile.seek(downloadLength); // 跳过已下载的字节
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while ((len = is.read(b)) != -1) {
                    total += len;
                    saveFile.write(b, 0, len);
                    int progress = (int) ((total + downloadLength) * 100 / contentLength); // 计算已下载的百分比
                    publishProgress((int) file.length(), progress); // 已下载文件的大小
                }
                response.body().close();
                return SUCCESS;
            } else {
                return FAILED;
            }
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (saveFile != null) {
                    saveFile.close();
                }
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
        return FAILED;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        // 后台任务中调用publishProgress(Progress...)后，此方法很快就会被回调
        int progress = values[0];
        int percentage = values[1];
        if (progress > lastProgress && percentage > 0) {
            if (listener != null) {
                listener.onProgressUpdate(progress, contentLength, percentage);
            }
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer status) {
        switch (status) {
            case SUCCESS:
                if (listener != null) {
                    if (file != null && file.isFile()) {
                        listener.onSuccess(file);
                    } else {
                        listener.onFailed();
                    }
                }
                break;
            case FAILED:
                if (listener != null) {
                    listener.onFailed();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 获取待下载文件的大小
     *
     * @param downloadUrl 下载地址
     * @return
     * @throws IOException
     */
    private long getFileLength(String downloadUrl) throws IOException {
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(downloadUrl)
//                .build();
//        Response response = client.newCall(request).execute();
//        if (response != null && response.isSuccessful()) {
//            long contentLength = response.body().contentLength();
//            response.close();
//            return contentLength;
//        }
//        return 0;
        try {
            URL url = new URL(downloadUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.setRequestProperty("Accept-Encoding", "identity");
            httpURLConnection.connect();
//            int length = httpURLConnection.getContentLength();
            String b = httpURLConnection.getHeaderField("Accept-Length");
            if (!TextUtils.isEmpty(b)) {
                return Long.parseLong(b);
            } else {
                return 0;
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return 0;
    }

}
