package common.android.https.builder;

import com.android.lib.Logger;

import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import common.android.https.callback.OkHttpCallback;
import common.android.https.network.OkHttpRequestBuilderHasParam;
import common.android.https.response.NetworkOkHttpResponse;
import common.android.https.network.NetWorkRequest;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * date: 2019/2/13
 * desc: 上传文件
 */
public class UploadBuilder extends OkHttpRequestBuilderHasParam<UploadBuilder> {

    private static final String TAG = "UploadBuilder";
    private Map<String, File> mFiles;
    private List<String> filePath;

    public UploadBuilder(NetWorkRequest request) {
        super(request);
    }

    /**
     * add upload files
     *
     * @param files files
     * @return this
     */
    public UploadBuilder files(Map<String, File> files) {
        this.mFiles = files;
        return this;
    }

    /**
     * add one upload file
     *
     * @param key  file key
     * @param file file
     * @return this
     */
    public UploadBuilder addFile(String key, File file) {
        if (this.mFiles == null) {
            mFiles = new LinkedHashMap<>();
        }
        mFiles.put(key, file);
        return this;
    }

    /**
     * add upload files
     *
     * @param filePath files
     * @return this
     */
    public UploadBuilder files(List<String> filePath) {
        this.filePath = filePath;
        return this;
    }

    /**
     * add one upload file
     *
     * @param file filePath
     * @return this
     */
    public UploadBuilder addFile(String file) {
        if (this.filePath == null) {
            filePath = new ArrayList<>();
        }
        filePath.add(file);
        return this;
    }

    @Override
    public void enqueue(int requestCode, NetworkOkHttpResponse okHttpResponse) {
        try {
            if (mUrl == null || mUrl.length() == 0) {
                throw new IllegalArgumentException("url can not be null !");
            }
//            if (mParams != null && mParams.size() > 0) {
//                mUrl = appendParams(mUrl, mParams); // 拼接参数(url后面)
//            }
            Request.Builder builder = new Request.Builder().url(mUrl);
            appendHeaders(builder, mHeaders); // 根据需要添加head
            if (mTag != null) {
                builder.tag(mTag);
            }
            MultipartBody.Builder multipartBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM); // 表单类型
            appendParams(multipartBuilder, mParams); // from参数
            // 拼装需要上传的文件参数,两种拼接上传文件的参数(二选其一)
            appendMapFiles(multipartBuilder, mFiles); // file文件
            appendListFiles(multipartBuilder, filePath); // 文件的路径
            builder.post(multipartBuilder.build());
            Request uploadRequest = builder.build();
            request.getOkHttpClient()
                    .newCall(uploadRequest)
                    .enqueue(new OkHttpCallback(requestCode, okHttpResponse));
        } catch (Exception e) {
            Logger.e(TAG, "Post Upload File enqueue error:" + e.getMessage());
            okHttpResponse.onDataFailure(requestCode, 0, e.getMessage(), false);
        }
    }

    /**
     * append files into MultipartBody builder 根据需要使用
     *
     * @param builder 构建
     * @param files   map数据源
     */
    private void appendMapFiles(MultipartBody.Builder builder, Map<String, File> files) {
        if (files != null && !files.isEmpty()) {
            for (String key : files.keySet()) {
                File file = files.get(key);
                // MediaType.parse() 里面是上传的文件类型。
//                RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
                if (file != null) {
//                    RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    // File转RequestBody
                    MediaType mediaType = MediaType.Companion.parse("text/x-markdown; charset=utf-8");
                    RequestBody body = RequestBody.Companion.create(file, mediaType);
                    builder.addFormDataPart(key, file.getName(), body);
                }
            }
        }
    }

    /**
     * append files into MultipartBody builder 根据需要使用
     *
     * @param builder  构建
     * @param filePath list数据源
     */
    private void appendListFiles(MultipartBody.Builder builder, List<String> filePath) {
        if (filePath != null && !filePath.isEmpty()) {
            for (int i = 0; i < filePath.size(); i++) {
                File file = new File(filePath.get(i));
                // MediaType.parse() 里面是上传的文件类型。
//                RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
//                RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                // File转RequestBody
                MediaType mediaType = MediaType.Companion.parse("text/x-markdown; charset=utf-8");
                RequestBody body = RequestBody.Companion.create(file, mediaType);
                builder.addFormDataPart("file", file.getName(), body);
            }
        }
    }

    /**
     * 获取mime type  image/png&image/jpg...
     *
     * @param path 文件路径
     * @return 文件类型
     */
    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

}
