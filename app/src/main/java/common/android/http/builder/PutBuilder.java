package common.android.http.builder;

import com.android.lib.Logger;

import common.android.http.callback.OkHttpCallback;
import common.android.http.okhttp.OkHttpRequestBuilder;
import common.android.http.response.CommonOkHttpResponse;
import common.android.http.retrofit.NetWorkRequest;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * date: 2019/2/13
 * desc: put请求
 */
public class PutBuilder extends OkHttpRequestBuilder<PutBuilder> {

    private static final String TAG = "PutBuilder";

    public PutBuilder(NetWorkRequest request) {
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
            builder.put(RequestBody.create(MediaType.parse("text/plain;charset=utf-8"), ""));
            Request putRequest = builder.build();
            request.getOkHttpClient()
                    .newCall(putRequest)
                    .enqueue(new OkHttpCallback(requestCode, okHttpResponse));
        } catch (Exception e) {
            Logger.e(TAG, "Put enqueue error:" + e.getMessage());
            okHttpResponse.onDataFailure(requestCode, 0, e.getMessage(), false);
        }
    }

}