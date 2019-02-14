package commom.android.http.builder;

import com.android.lib.Logger;

import commom.android.http.response.CommonOkHttpResponse;
import commom.android.http.callback.OkHttpCallback;
import commom.android.http.okhttp.OkHttpRequestBuilderHasParam;
import commom.android.http.retrofit.NetWorkRequest;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @date: 2019/2/13
 * @describe: post请求
 */
public class PostBuilder extends OkHttpRequestBuilderHasParam<PostBuilder> {

    private static final String TAG = "PostBuilder";
    private String mJsonParams = "";

    public PostBuilder(NetWorkRequest request) {
        super(request);
    }

    /**
     * json格式参数(优先)
     *
     * @param json
     * @return
     */
    public PostBuilder jsonParams(String json) {
        this.mJsonParams = json;
        return this;
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
            if (mJsonParams.length() > 0) { // 优先提交json格式参数
                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), mJsonParams);
                builder.post(body);
            } else { // 普通kv参数
                FormBody.Builder encodingBuilder = new FormBody.Builder();
                appendParams(encodingBuilder, mParams);
                builder.post(encodingBuilder.build());
            }
            Request postRequest = builder.build();
            request.getOkHttpClient()
                    .newCall(postRequest)
                    .enqueue(new OkHttpCallback(requestCode, okHttpResponse));
        } catch (Exception e) {
            Logger.e(TAG, "Post enqueue error:" + e.getMessage());
            okHttpResponse.onDataFailure(requestCode, 0, e.getMessage(), false);
        }
    }

}
