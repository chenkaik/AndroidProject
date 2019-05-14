package common.android.http.okhttp;


import com.android.lib.Logger;

import java.io.IOException;

import common.android.http.config.UserConfig;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * date: 2019/1/30
 * desc: Request拦截器，添加header--token
 */
public class OkHttpInterceptor implements Interceptor {

    private static final String TAG = "OkHttpInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
//        Request original = chain.request();
//        // 获取、修改请求头
//        Request.Builder builder = original.newBuilder();
//        builder.addHeader("Authorization", "Bearer " + UserConfig.getInstance().getToken());
//        Request request = builder.build();
//
//        Response response = chain.proceed(request);
//        ResponseBody body = response.body();
//        if (body != null && body.contentLength() == 0) {
//            //Log.e(TAG, "length = 0");
//        }
        Request original = chain.request();

        // 获取、修改请求头
        Headers headers = original.headers();
        Headers newHeader = headers.newBuilder()
                .add("Authorization", "Bearer " + UserConfig.getInstance().getToken())
//                .add("Authorization", "Bearer 038cc6ca9ec03a38b18370bf1b9b7b15")
                .build();

        Request.Builder builder = original.newBuilder()
                .headers(newHeader);

        Request request = builder.build();

        Response response = chain.proceed(request);

        ResponseBody body = response.body();
        if (body != null && body.contentLength() == 0) {
            Logger.e(TAG, "length = 0");
        }
        return response;
    }

}
