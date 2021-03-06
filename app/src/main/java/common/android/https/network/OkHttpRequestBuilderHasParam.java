package common.android.https.network;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MultipartBody;

/**
 * date: 2019/2/13
 * desc: 带有param的base request body
 */
public abstract class OkHttpRequestBuilderHasParam<T extends OkHttpRequestBuilderHasParam> extends OkHttpRequestBuilder<T> {

    protected Map<String, String> mParams;

    public OkHttpRequestBuilderHasParam(NetWorkRequest request) {
        super(request);
    }

    /**
     * set Map params
     *
     * @param params 参数
     * @return T
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
     * @return T
     */
    public T addParam(String key, String val) {
        if (this.mParams == null) {
            mParams = new LinkedHashMap<>();
        }
        mParams.put(key, val);
        return (T) this;
    }

    /**
     * append params to url
     *
     * @param url    接口路径
     * @param params 参数
     * @return 拼接后的url
     */
    public String appendParams(String url, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(url + "?");
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                sb.append(key).append("=").append(params.get(key)).append("&");
            }
        }
        sb = sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * append params to form builder
     *
     * @param builder 构建
     * @param params  参数
     */
    public void appendParams(FormBody.Builder builder, Map<String, String> params) {
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
    }

    /**
     * append params into MultipartBody builder
     *
     * @param builder 构建
     * @param params  参数(form提交)
     */
    public void appendParams(MultipartBody.Builder builder, Map<String, String> params) {
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
//                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
//                        RequestBody.create(null, params.get(key)));
                builder.addFormDataPart(key, params.get(key));
            }
//            for (Map.Entry entry : map.entrySet()) {
//                requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
//            }
        }
    }

}
