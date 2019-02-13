package commom.android.http.okhttp;

import java.util.LinkedHashMap;
import java.util.Map;

import commom.android.http.retrofit.NetWorkRequest;
import okhttp3.FormBody;

/**
 * @date: 2019/2/13
 * @describe: 带有param的base request body
 */
public abstract class OkHttpRequestBuilderHasParam<T extends OkHttpRequestBuilderHasParam> extends OkHttpRequestBuilder<T> {

    protected Map<String, String> mParams;

    public OkHttpRequestBuilderHasParam(NetWorkRequest request) {
        super(request);
    }

    /**
     * set Map params
     *
     * @param params
     * @return
     */
    public T params(Map<String, String> params) {
        this.mParams = params;
        return (T) this;
    }

    /**
     * add param
     *
     * @param key param key
     * @param val param val
     * @return
     */
    public T addParam(String key, String val) {
        if (this.mParams == null) {
            mParams = new LinkedHashMap<>();
        }
        mParams.put(key, val);
        return (T) this;
    }


    /**
     * append params to form builder
     *
     * @param builder
     * @param params
     */
    public void appendParams(FormBody.Builder builder, Map<String, String> params) {
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
    }

}
