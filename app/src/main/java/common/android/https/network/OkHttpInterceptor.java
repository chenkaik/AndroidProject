package common.android.https.network;


import com.android.lib.Logger;
import com.example.android.project.util.UserConfig;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

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

    @NotNull
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
//                .add("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6IjE1Njg2MjIxODEzIiwiZXhwIjoxNTg3MjcyMDM0LCJ1c2VySWQiOjEwfQ.DUjajZqGDmWnWiUaKFRWpISzf0zf0qN6hfE8uCDAXlk")
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
