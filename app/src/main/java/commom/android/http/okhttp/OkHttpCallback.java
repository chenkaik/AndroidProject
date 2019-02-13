package commom.android.http.okhttp;

import com.android.lib.Logger;
import com.android.lib.util.GsonUtil;

import java.io.IOException;

import commom.android.http.config.HttpConfig;
import commom.android.http.retrofit.BaseResponse;
import commom.android.http.retrofit.NetWorkRequest;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @date: 2019/2/13
 * @describe: okHttp 回调
 */
public class OkHttpCallback implements Callback {

    private static final String TAG = "OkHttpCallback";
    private int requestCode;
    private CommonOkHttpResponse okHttpResponse;
    private String responseBodyStr = "";

    public OkHttpCallback(int requestCode, CommonOkHttpResponse okHttpResponse) {
        this.requestCode = requestCode;
        this.okHttpResponse = okHttpResponse;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        Logger.e("onFailure: ", "警告,调用接口出错!", e);
        NetWorkRequest.mHandler.post(new Runnable() {
            @Override
            public void run() {
                okHttpResponse.onDataError(requestCode, 400, "系统异常(400),请联系管理员!", false);
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) {
        Logger.e(TAG, "onResponse: 响应码" + response.code());
        if (response.isSuccessful()) { // code >= 200 && code < 300;
            ResponseBody responseBody = response.body();
            try {
                if (responseBody != null) {
                    responseBodyStr = responseBody.string();
                    Logger.e(TAG, "onResponse: " + responseBodyStr);
                    final BaseResponse baseResponse = GsonUtil.fromJson(responseBodyStr, BaseResponse.class);
                    if (baseResponse != null) {
                        if (baseResponse.getSYS_HEAD() != null && baseResponse.getSYS_HEAD().getRET() != null) {
                            if (HttpConfig.SUCCESS.equals(baseResponse.getSYS_HEAD().getRET().getRET_CODE())) {
                                NetWorkRequest.mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        okHttpResponse.onDataReady(requestCode, null, responseBodyStr);
                                    }
                                });
                            } else if (HttpConfig.TOKENERROR.equals(baseResponse.getSYS_HEAD().getRET().getRET_CODE())) {
                                NetWorkRequest.mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        okHttpResponse.onDataError(requestCode, 0, "登录失效!", true);
                                    }
                                });
                            } else {
                                NetWorkRequest.mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        okHttpResponse.onDataError(requestCode, 0, baseResponse.getSYS_HEAD().getRET().getRET_CODE() + " " + baseResponse.getSYS_HEAD().getRET().getRET_MSG(), false);
                                    }
                                });
                            }
                        }
                    } else {
                        NetWorkRequest.mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                okHttpResponse.onDataError(requestCode, 0, "解析数据异常!", false);
                            }
                        });
                    }
//                    if (requestCode == 1) { // 登录
//                        BaseOkResponse<LoginOkResponse> data = GsonUtil.fromJson(responseBodyStr, new TypeToken<BaseOkResponse<LoginOkResponse>>() {
//                        });
//                    }
                }
            } catch (Exception e) {
                Logger.e("onResponse", "onResponse json fail status=" + response.code());
                NetWorkRequest.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        okHttpResponse.onDataError(requestCode, 0, "解析数据异常!", false);
                    }
                });
            } finally {
                if (responseBody != null) {
                    responseBody.close();
                }
            }
        } else {
            Logger.e("onResponse", "onResponse fail status=" + response.code());
            NetWorkRequest.mHandler.post(new Runnable() {
                @Override
                public void run() {
                    okHttpResponse.onDataError(requestCode, 500, "系统异常(500),请联系管理员!", false);
                }
            });
        }
    }

}
