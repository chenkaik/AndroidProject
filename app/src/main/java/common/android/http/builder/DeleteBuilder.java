package common.android.http.builder;

import com.android.lib.Logger;

import common.android.http.callback.OkHttpCallback;
import common.android.http.okhttp.OkHttpRequestBuilderHasParam;
import common.android.http.response.NetworkOkHttpResponse;
import common.android.http.retrofit.NetWorkRequest;
import okhttp3.Request;

/**
 * date: 2019/2/13
 * desc: delete请求
 */
public class DeleteBuilder extends OkHttpRequestBuilderHasParam<DeleteBuilder> {

    private static final String TAG = "DeleteBuilder";

    public DeleteBuilder(NetWorkRequest request) {
        super(request);
    }

    @Override
    public void enqueue(int requestCode, NetworkOkHttpResponse okHttpResponse) {
        try {
            if (mUrl == null || mUrl.length() == 0) {
                throw new IllegalArgumentException("url can not be null !");
            }
            Request.Builder builder = new Request.Builder().url(mUrl).delete();
            appendHeaders(builder, mHeaders); // 根据需要添加head
            if (mTag != null) {
                builder.tag(mTag);
            }
            Request deleteRequest = builder.build();
            request.getOkHttpClient()
                    .newCall(deleteRequest)
                    .enqueue(new OkHttpCallback(requestCode, okHttpResponse));
        } catch (Exception e) {
            Logger.e(TAG, "Delete enqueue error:" + e.getMessage());
            okHttpResponse.onDataFailure(requestCode, 0, e.getMessage(), false);
        }
    }

}
