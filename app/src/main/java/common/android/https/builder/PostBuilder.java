package common.android.https.builder;

import com.android.lib.Logger;

import common.android.https.callback.OkHttpCallback;
import common.android.https.network.OkHttpRequestBuilderHasParam;
import common.android.https.response.NetworkOkHttpResponse;
import common.android.https.network.NetWorkRequest;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * date: 2019/2/13
 * desc: post请求
 * https://blog.csdn.net/qq_19306415/article/details/102954712?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522158659699019726867824966%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=158659699019726867824966&biz_id=0&utm_source=distribute.pc_search_result.none-task-blog-soetl_so_first_rank_v2_rank_v25-1
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
     * @param json 提交的json数据
     * @return this
     */
    public PostBuilder jsonParams(String json) {
        this.mJsonParams = json;
        return this;
    }

    @Override
    public void enqueue(int requestCode, NetworkOkHttpResponse okHttpResponse) {
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
