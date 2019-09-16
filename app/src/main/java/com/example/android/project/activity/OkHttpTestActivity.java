package com.example.android.project.activity;

import android.view.View;

import com.android.lib.Logger;
import com.android.lib.util.GsonUtil;
import com.android.lib.widget.NavigationBar;
import com.example.android.project.R;
import com.example.android.project.entity.LoginResponse;
import com.example.android.project.entity.TradeNumberResponse;
import com.example.android.project.util.UserConfig;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import common.android.https.network.DownloadResponseHandler;
import common.android.https.response.NetworkOkHttpResponse;
import common.android.https.network.NetWorkRequest;

public class OkHttpTestActivity extends BaseActivity implements NetworkOkHttpResponse {

    private static final String TAG = "OkHttpTestActivity";

    @BindView(R.id.navigationBar)
    NavigationBar mNavigationBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ok_http_test;
    }

    @Override
    protected void initView() {
        mNavigationBar.setTitle("okHttp请求");
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_login_ok, R.id.btn_other_ok, R.id.btn_ok_download})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_ok:
                showLoadingDialog("");
                UserConfig.getInstance().clearToken();
                Map<String, String> params = new HashMap<>();
                params.put("username", "lixiangbin");
                params.put("password", "shjacf");
                NetWorkRequest.getRequestManager()
                        .post()
                        .url("https://www.shjacf.com/server/login")
                        .jsonParams(new JSONObject(params).toString())
                        .tag(this)
                        .enqueue(1, this);
                break;
            case R.id.btn_other_ok:
                showLoadingDialog("");
                NetWorkRequest.getRequestManager()
                        .get()
                        .url("https://www.shjacf.com/server/api-user/file/reference/PW")
//                        .addHeader("Authorization","Bearer 791a88484f833a66b67b9b60395027ec")
//                        .addParam("name", "test")
                        .tag(this)
                        .enqueue(2, this);
                break;
            case R.id.btn_ok_download:
                UserConfig.getInstance().clearToken();
                String url = "http://download.taobaocdn.com/wireless/xiami-android-spark/latest/xiami-android-spark_701287.apk";
                NetWorkRequest.getRequestManager()
                        .download()
                        .url(url)
                        .filePath(getExternalCacheDir() + "/xiaoMi.apk")
                        .tag(this)
                        .enqueue(new DownloadResponseHandler() {
                            @Override
                            public void onStart(long totalBytes) {
                                Logger.e(TAG, "doDownload onStart " + totalBytes);
                            }

                            @Override
                            public void onFinish(File downloadFile) {
                                Logger.e(TAG, "doDownload onFinish:" + downloadFile.getPath());
                            }

                            @Override
                            public void onProgress(long currentBytes, long totalBytes) {
                                Logger.e(TAG, "doDownload onProgress:" + currentBytes + "/" + totalBytes);
                            }

                            @Override
                            public void onFailure(String error_msg) {
                                Logger.e(TAG, "doDownload onFailure:" + error_msg);
                            }
                        });
                break;
            default:
                break;
        }
    }

//    @Override
//    public void onSuccess(int requestCode, Response response) {
//
//    }

    @Override
    public void onDataSuccess(int requestCode, Object object, String json) {
        dismissLoadingDialog();
        Logger.e(TAG, "onDataSuccess: " + json);
        switch (requestCode) {
            case 1:
                LoginResponse response = GsonUtil.fromJson(json, LoginResponse.class);
                if (response != null) {
                    UserConfig.getInstance().putToken(response.getMSG_BODY().getToken());
                    showToastMessage("登录成功");
                }
                break;
            case 2:
                TradeNumberResponse tradeNumberResponse = GsonUtil.fromJson(json, TradeNumberResponse.class);
                if (tradeNumberResponse != null) {
                    showToastMessage(tradeNumberResponse.getMSG_BODY().getResult());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onDataFailure(int requestCode, int responseCode, String message, boolean isOverdue) {
        dismissLoadingDialog();
        commonFail(message, isOverdue);
    }

//    @Override
//    public void showLoading(String msg) {
//
//    }
//
//    @Override
//    public void dismissLoading() {
//
//    }
}
