package commom.android.http.builder;

import com.android.lib.Logger;

import commom.android.http.callback.OkHttpCallback;
import commom.android.http.okhttp.OkHttpRequestBuilder;
import commom.android.http.response.CommonOkHttpResponse;
import commom.android.http.retrofit.NetWorkRequest;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @date: 2019/2/13
 * @describe: patch请求
 */
public class PatchBuilder extends OkHttpRequestBuilder<PatchBuilder> {

    private static final String TAG = "PatchBuilder";

    public PatchBuilder(NetWorkRequest request) {
        super(request);
    }

    @Override
    public void enqueue(int requestCode, CommonOkHttpResponse okHttpResponse) {
        try {
            if (mUrl == null || mUrl.length() == 0) {
                throw new IllegalArgumentException("url can not be null !");
            }
            Request.Builder builder = new Request.Builder().url(mUrl);
            appendHeaders(builder, mHeaders); // 根据需要添加head
            if (mTag != null) {
                builder.tag(mTag);
            }
            builder.patch(RequestBody.create(MediaType.parse("text/plain;charset=utf-8"), ""));
            Request patchRequest = builder.build();
            request.getOkHttpClient()
                    .newCall(patchRequest)
                    .enqueue(new OkHttpCallback(requestCode, okHttpResponse));
        } catch (Exception e) {
            Logger.e(TAG, "Patch enqueue error:" + e.getMessage());
            okHttpResponse.onDataError(requestCode, 0, e.getMessage(), false);
        }
    }

}
