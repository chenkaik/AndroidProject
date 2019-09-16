package common.android.https.builder;

import com.android.lib.Logger;

import common.android.https.callback.OkHttpCallback;
import common.android.https.network.OkHttpRequestBuilderHasParam;
import common.android.https.response.NetworkOkHttpResponse;
import common.android.https.network.NetWorkRequest;
import okhttp3.Request;

/**
 * date: 2019/2/13
 * desc: get请求
 */
public class GetBuilder extends OkHttpRequestBuilderHasParam<GetBuilder> {

    private static final String TAG = "GetBuilder";

    public GetBuilder(NetWorkRequest request) {
        super(request);
    }

    @Override
    public void enqueue(int requestCode, NetworkOkHttpResponse okHttpResponse) {
        try {
            if (mUrl == null || mUrl.length() == 0) {
                throw new IllegalArgumentException("url can not be null !");
            }
            if (mParams != null && mParams.size() > 0) {
                mUrl = appendParams(mUrl, mParams); // 拼接参数
            }
            Request.Builder builder = new Request.Builder().url(mUrl).get();
            appendHeaders(builder, mHeaders); // 根据需要添加head
            if (mTag != null) {
                builder.tag(mTag);
            }
            Request getRequest = builder.build();
            request.getOkHttpClient()
                    .newCall(getRequest)
                    .enqueue(new OkHttpCallback(requestCode, okHttpResponse));
        } catch (Exception e) {
            Logger.e(TAG, "Get enqueue error:" + e.getMessage());
            okHttpResponse.onDataFailure(requestCode, 0, e.getMessage(), false);
        }
    }

}
