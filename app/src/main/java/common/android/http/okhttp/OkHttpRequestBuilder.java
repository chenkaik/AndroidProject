package common.android.http.okhttp;

import java.util.LinkedHashMap;
import java.util.Map;

import common.android.http.response.CommonOkHttpResponse;
import common.android.http.retrofit.NetWorkRequest;
import okhttp3.Headers;
import okhttp3.Request;

/**
 * date: 2019/2/13
 * desc: 不带param的base request body
 */
public abstract class OkHttpRequestBuilder<T extends OkHttpRequestBuilder> {

    protected String mUrl;
    protected Object mTag;
    protected Map<String, String> mHeaders;
    protected NetWorkRequest request;

    public OkHttpRequestBuilder(NetWorkRequest request) {
        this.request = request;
    }

    /**
     * 异步执行
     *
     * @param requestCode    区分请求的code
     * @param okHttpResponse 自定义回调
     */
    public abstract void enqueue(int requestCode, CommonOkHttpResponse okHttpResponse);


    /**
     * set url
     *
     * @param url url
     * @return T
     */
    public T url(String url) {
        this.mUrl = url;
        return (T) this;
    }

    /**
     * set tag
     *
     * @param tag tag
     * @return T
     */
    public T tag(Object tag) {
        this.mTag = tag;
        return (T) this;
    }

    /**
     * set headers
     *
     * @param headers headers
     * @return T
     */
    public T headers(Map<String, String> headers) {
        this.mHeaders = headers;
        return (T) this;
    }

    /**
     * set one header
     *
     * @param key header key
     * @param val header val
     * @return T
     */
    public T addHeader(String key, String val) {
        if (this.mHeaders == null) {
            mHeaders = new LinkedHashMap<>();
        }
        mHeaders.put(key, val);
        return (T) this;
    }

    /**
     * append headers into builder
     *
     * @param builder Request.Builder
     * @param headers head参数
     */
    public void appendHeaders(Request.Builder builder, Map<String, String> headers) {
        if (headers == null || headers.isEmpty()) {
            return;
        }
        Headers.Builder headerBuilder = new Headers.Builder();
        for (String key : headers.keySet()) {
            headerBuilder.add(key, headers.get(key));
        }
        builder.headers(headerBuilder.build());
    }

}
