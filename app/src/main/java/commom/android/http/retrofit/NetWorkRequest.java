package commom.android.http.retrofit;

import android.os.Handler;
import android.os.Looper;

import com.android.lib.Logger;
import com.android.lib.util.NetErrStringUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import commom.android.http.config.CookieManager;
import commom.android.http.config.HttpConfig;
import commom.android.http.config.OkHttpInterceptor;
import commom.android.http.okhttp.PostBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @date: 2019/1/30
 * @describe: retrofit调用接口，支持网络请求缓存，自动添加和删除缓存，也可以手动cancel请求
 */
public class NetWorkRequest {

    private static final String TA = NetWorkRequest.class.getSimpleName();
    private Map<String, Map<Integer, Call>> mRequestMap = new ConcurrentHashMap<>();
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;
    public static Handler mHandler = new Handler(Looper.getMainLooper());

    private NetWorkRequest() {
    }

    public static NetWorkRequest getInstance() {
        return NetWorkRequestHolder.sInstance;
    }

    private static class NetWorkRequestHolder {
        private static final NetWorkRequest sInstance = new NetWorkRequest();
    }

    /**
     * 初始化Retrofit
     *
     * @param baseURL 主机地址
     * @return
     */
    public NetWorkRequest init(String baseURL) {
        synchronized (NetWorkRequest.this) {
            mOkHttpClient = new OkHttpClient.Builder()
//                    .cache(new Cache(new File(context.getExternalCacheDir(), "http_cache"), 1024 * 1024 * 100))
                    .readTimeout(HttpConfig.READ_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(HttpConfig.WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(HttpConfig.CONNECT_TIMEOUT, TimeUnit.SECONDS)
//                    .cookieJar(new CookieManager())
//                    .authenticator(new AuthenticatorManager())
                    .addInterceptor(new OkHttpInterceptor())
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseURL) // 接口地址须以"/"结尾
                    .addConverterFactory(GsonConverterFactory.create()) // 支持Gson转换器
                    .addConverterFactory(ScalarsConverterFactory.create()) // 支持返回值为String
                    .client(mOkHttpClient)
                    .build();
        }
        return this;
    }

    public <T> T create(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }

    public void clearCookie() {
        ((CookieManager) mOkHttpClient.cookieJar()).clearCookie();
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient == null ? new OkHttpClient() : mOkHttpClient;
    }

    /**
     * 异步请求
     *
     * @param TAG              区分不同页面的请求
     * @param requestCode      用于区分相同页面的不同请求
     * @param requestCall      动态代理 泛型为返回实体 必须继承BaseResponseEntity
     * @param responseListener 接口回调 泛型为返回实体 必须继承BaseResponseEntity（可根据需要进行修改）
     * @param isShow           是否显示加载框
     * @param <T>
     */
    public <T extends BaseResponse> void asyncNetWork(final String TAG, final int requestCode, final Call<T> requestCall, final CommonResponse<T> responseListener, final boolean isShow) {
        if (responseListener == null) {
            return;
        }
        if (isShow) {
            responseListener.showLoading("");
        }
        Call<T> call;
        if (requestCall.isExecuted()) {
            call = requestCall.clone();
        } else {
            call = requestCall;
        }
        addCall(TAG, requestCode, call);
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                Logger.e(TA, "响应码:" + response.code());
                if (isShow) {
                    responseListener.dismissLoading();
                }
                cancelCall(TAG, requestCode);
                if (response.isSuccessful()) {
                    T result = response.body();
                    if (result == null) {
                        responseListener.onDataError(requestCode, response.code(), "数据加载失败", false);
                        return;
                    }
                    if (result.getSYS_HEAD() != null && result.getSYS_HEAD().getRET() != null) {
                        if (HttpConfig.SUCCESS.equals(result.getSYS_HEAD().getRET().getRET_CODE())) {
                            result.requestCode = requestCode; // 区分接口请求
                            responseListener.onDataReady(result);
                        } else if (HttpConfig.TOKENERROR.equals(result.getSYS_HEAD().getRET().getRET_CODE())) {
                            responseListener.onDataError(requestCode, response.code(), "登录失效", true);
                        } else {
                            responseListener.onDataError(requestCode, response.code(), result.getSYS_HEAD().getRET().getRET_CODE() + " " + result.getSYS_HEAD().getRET().getRET_MSG(), false);
                        }
                    }
                } else {
                    responseListener.onDataError(requestCode, response.code(), NetErrStringUtils.getErrString(response.code()), false);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (isShow) {
                    responseListener.dismissLoading();
                }
                cancelCall(TAG, requestCode);
                responseListener.onDataError(requestCode, 0, NetErrStringUtils.getErrString(t), false);
            }
        });
    }

    /**
     * 同步请求(使用同步请求需单独处理子线程问题)
     *
     * @param TAG              区分不同页面的请求
     * @param requestCode      用于区分相同页面的不同请求
     * @param requestCall      动态代理 泛型为返回实体 必须继承BaseResponseEntity
     * @param responseListener 接口回调 泛型为返回实体 必须继承BaseResponseEntity（可根据需要进行修改）
     * @param isShow           是否显示加载框
     * @param <T>
     */
    public <T extends BaseResponse> void syncNetWork(final String TAG, final int requestCode, final Call<T> requestCall, final CommonResponse<T> responseListener, boolean isShow) {
        if (responseListener == null) {
            return;
        }
        if (isShow) {
            responseListener.showLoading("");
        }
        Call<T> call;
        try {
            if (requestCall.isExecuted()) {
                call = requestCall.clone();
            } else {
                call = requestCall;
            }
            Response<T> response = call.execute();
            addCall(TAG, requestCode, call);
            if (response.isSuccessful()) {
                T result = response.body();
                if (result == null) {
                    responseListener.onDataError(requestCode, response.code(), "数据加载失败", false);
                    return;
                }
//                result.requestCode = requestCode;
//                result.serverTip = response.message();
//                result.responseCode = response.code();
                responseListener.onDataReady(result);
            } else {
                responseListener.onDataError(requestCode, response.code(), NetErrStringUtils.getErrString(response.code()), false);
            }
        } catch (IOException e) {
            responseListener.onDataError(requestCode, 0, NetErrStringUtils.getErrString(e), false);
        } finally {
            if (isShow) {
                responseListener.dismissLoading();
            }
            cancelCall(TAG, requestCode);
        }
    }

    /**
     * 添加call到Map
     *
     * @param TAG
     * @param call
     */
    private void addCall(String TAG, Integer code, Call call) {
        if (TAG == null) {
            return;
        }
        if (mRequestMap.get(TAG) == null) {
            Map<Integer, Call> map = new ConcurrentHashMap<>();
            map.put(code, call);
            mRequestMap.put(TAG, map);
        } else {
            mRequestMap.get(TAG).put(code, call);
        }
    }

    /**
     * 取消某个call
     *
     * @param TAG
     * @param code
     */
    public boolean cancelCall(String TAG, Integer code) {
        if (TAG == null) {
            return false;
        }
        Map<Integer, Call> map = mRequestMap.get(TAG);
        if (map == null) {
            return false;
        }
        if (code == null) {
            // 取消整个context请求
            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                Integer key = (Integer) iterator.next();
                Call call = map.get(key);
                if (call == null) {
                    continue;
                }
                call.cancel();
            }
            mRequestMap.remove(TAG);
            return false;
        } else {
            // 取消一个请求
            if (map.containsKey(code)) {
                Call call = map.get(code);
                if (call != null) {
                    call.cancel();
                }
                map.remove(code);
            }
            if (map.size() == 0) {
                mRequestMap.remove(TAG);
                return false;
            }
        }
        return true;
    }

    public void release() {
        mOkHttpClient = null;
        mRetrofit = null;
    }

    /**
     * 取消整个tag请求，关闭页面时调用
     *
     * @param TAG
     */
    public void cancelTagCall(String TAG) {
        cancelCall(TAG, null);
    }

    public PostBuilder post() {
        return new PostBuilder(this);
    }

}
