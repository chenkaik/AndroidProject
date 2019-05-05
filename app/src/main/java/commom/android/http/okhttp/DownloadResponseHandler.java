package commom.android.http.okhttp;

import java.io.File;

/**
 * date: 2019/2/18
 * desc: 下载文件回调
 */
public abstract class DownloadResponseHandler {

    public void onStart(long totalBytes) {

    }

    public void onCancel() {

    }

    public abstract void onFinish(File downloadFile);

    public abstract void onProgress(long currentBytes, long totalBytes);

    public abstract void onFailure(String error_msg);

}
