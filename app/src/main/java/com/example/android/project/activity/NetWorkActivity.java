package com.example.android.project.activity;

import android.view.View;

import com.android.lib.Logger;
import com.android.lib.util.GsonUtil;
import com.example.android.project.R;
import com.example.android.project.databinding.ActivityNetWorkBinding;
import com.example.android.project.databinding.CommonHeadLayoutBinding;
import com.example.android.project.entity.HomeIndexResponse;
import com.example.android.project.entity.LoginResponse;
import com.example.android.project.entity.TradeNumberResponse;
import com.example.android.project.entity.request.HomeIndexRequest;
import com.example.android.project.entity.request.LoginRequest;
import com.example.android.project.util.UserConfig;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import common.android.https.ApiManager;
import common.android.https.network.DownloadResponseHandler;
import common.android.https.network.NetWorkRequest;
import common.android.https.response.BaseResponse;
import common.android.https.response.NetworkOkHttpResponse;
import common.android.https.response.NetworkResponse;

public class NetWorkActivity extends BaseActivity implements NetworkResponse, NetworkOkHttpResponse, View.OnClickListener {

    private static final String TAG = "NetWorkActivity";

    private LoginRequest loginRequest;
    private HomeIndexRequest request;

    private ActivityNetWorkBinding mActivityNetWorkBinding;
    private CommonHeadLayoutBinding mCommonHeadLayoutBinding;


    @Override
    protected void initView() {
        mActivityNetWorkBinding = ActivityNetWorkBinding.inflate(getLayoutInflater());
        setContentView(mActivityNetWorkBinding.getRoot());
        mCommonHeadLayoutBinding = mActivityNetWorkBinding.commonHead;
        mCommonHeadLayoutBinding.navigationBar.setTitle("网络请求");
        mActivityNetWorkBinding.btnLogin.setOnClickListener(this);
        mActivityNetWorkBinding.btnOther.setOnClickListener(this);
        mActivityNetWorkBinding.btnLoginOk.setOnClickListener(this);
        mActivityNetWorkBinding.btnOtherOk.setOnClickListener(this);
        mActivityNetWorkBinding.btnOkDownload.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        loginRequest = new LoginRequest();
        loginRequest.setUserName("lixiangbin");
        loginRequest.setPassword("shjacf");
        request = new HomeIndexRequest();
        request.setMSG_BODY(new HomeIndexRequest.MSGBODYBean());
    }

    //    @OnClick({R.id.btn_login, R.id.btn_other, R.id.btn_login_ok, R.id.btn_other_ok, R.id.btn_ok_download})
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_login) {
            UserConfig.getInstance().clearToken();
            NetWorkRequest.getRequestManager().asyncNetWork(TAG, 1, ApiManager.getInstance().getApiService().login(loginRequest), this, true);
        } else if (id == R.id.btn_other) {
            NetWorkRequest.getRequestManager().asyncNetWork(TAG, 100, ApiManager.getInstance().getApiService().homeIndex(request), this, true);
        } else if (id == R.id.btn_login_ok) {
            showLoadingDialog("");
            UserConfig.getInstance().clearToken();
            Map<String, String> params = new HashMap<>();
            params.put("username", "lixiangbin");
            params.put("password", "shjacf");
            NetWorkRequest.getRequestManager()
                    .post(false)
                    .url("https://www.shjacf.com/server/login")
                    .jsonParams(new JSONObject(params).toString())
                    .tag(this)
                    .enqueue(1, this);
        } else if (id == R.id.btn_other_ok) {
            showLoadingDialog("");
            NetWorkRequest.getRequestManager()
                    .get()
                    .url("https://www.shjacf.com/server/api-user/file/reference/PW")
//                        .addHeader("Authorization","Bearer 791a88484f833a66b67b9b60395027ec")
//                        .addParam("name", "test")
                    .tag(this)
                    .enqueue(2, this);
        } else {
            getExternalFilesDir("test");
            UserConfig.getInstance().clearToken();
//                String url = "http://download.taobaocdn.com/wireless/xiami-android-spark/latest/xiami-android-spark_701287.apk";
            String url = "http://staruml-7a0.kxcdn.com/releases/StarUML%20Setup%203.2.2.exe";
            NetWorkRequest.getRequestManager()
                    .download()
                    .url(url)
                    .filePath(getExternalCacheDir() + "/StarUML203.2.2.exe")
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
        }
//        switch (view.getId()) {
//            case R.id.btn_login:
//                UserConfig.getInstance().clearToken();
//                NetWorkRequest.getRequestManager().asyncNetWork(TAG, 1, ApiManager.getInstance().getApiService().login(loginRequest), this, true);
//                break;
//            case R.id.btn_other:
//                NetWorkRequest.getRequestManager().asyncNetWork(TAG, 100, ApiManager.getInstance().getApiService().homeIndex(request), this, true);
//                break;
//            case R.id.btn_login_ok:
//                showLoadingDialog("");
//                UserConfig.getInstance().clearToken();
//                Map<String, String> params = new HashMap<>();
//                params.put("username", "lixiangbin");
//                params.put("password", "shjacf");
//                NetWorkRequest.getRequestManager()
//                        .post(false)
//                        .url("https://www.shjacf.com/server/login")
//                        .jsonParams(new JSONObject(params).toString())
//                        .tag(this)
//                        .enqueue(1, this);
//                break;
//            case R.id.btn_other_ok:
//                showLoadingDialog("");
//                NetWorkRequest.getRequestManager()
//                        .get()
//                        .url("https://www.shjacf.com/server/api-user/file/reference/PW")
////                        .addHeader("Authorization","Bearer 791a88484f833a66b67b9b60395027ec")
////                        .addParam("name", "test")
//                        .tag(this)
//                        .enqueue(2, this);
//                break;
//            case R.id.btn_ok_download:
//                getExternalFilesDir("test");
//                UserConfig.getInstance().clearToken();
////                String url = "http://download.taobaocdn.com/wireless/xiami-android-spark/latest/xiami-android-spark_701287.apk";
//                String url = "http://staruml-7a0.kxcdn.com/releases/StarUML%20Setup%203.2.2.exe";
//                NetWorkRequest.getRequestManager()
//                        .download()
//                        .url(url)
//                        .filePath(getExternalCacheDir() + "/StarUML203.2.2.exe")
//                        .tag(this)
//                        .enqueue(new DownloadResponseHandler() {
//                            @Override
//                            public void onStart(long totalBytes) {
//                                Logger.e(TAG, "doDownload onStart " + totalBytes);
//                            }
//
//                            @Override
//                            public void onFinish(File downloadFile) {
//                                Logger.e(TAG, "doDownload onFinish:" + downloadFile.getPath());
//                            }
//
//                            @Override
//                            public void onProgress(long currentBytes, long totalBytes) {
//                                Logger.e(TAG, "doDownload onProgress:" + currentBytes + "/" + totalBytes);
//                            }
//
//                            @Override
//                            public void onFailure(String error_msg) {
//                                Logger.e(TAG, "doDownload onFailure:" + error_msg);
//                            }
//                        });
//                break;
//            default:
//                break;
//        }
    }


    @Override
    public void onDataReady(BaseResponse response) {
        switch (response.requestCode) {
            case 1:
                LoginResponse loginResponseEntity = (LoginResponse) response;
                showToastMessage("Retrofit成功1");
                UserConfig.getInstance().putToken(loginResponseEntity.getMSG_BODY().getToken());
                Logger.e(TAG, ":111111");
                break;
            case 100:
                HomeIndexResponse homeIndexResponse = (HomeIndexResponse) response;
                showToastMessage("Retrofit成功100");
                break;
            default:
                break;
        }
    }

    @Override
    public void onDataError(int requestCode, int responseCode, String message, boolean isOverdue) {
        commonFail(message, isOverdue);
    }

    @Override
    public void showLoading(String msg) {
        showLoadingDialog(msg);
    }

    @Override
    public void dismissLoading() {
        dismissLoadingDialog();
    }

    @Override
    public void onDataSuccess(int requestCode, Object object, String json) {
        dismissLoadingDialog();
        Logger.e(TAG, "onDataSuccess: " + json);
        switch (requestCode) {
            case 1:
                LoginResponse response = GsonUtil.fromJson(json, LoginResponse.class);
                if (response != null) {
                    UserConfig.getInstance().putToken(response.getMSG_BODY().getToken());
                    showToastMessage("OkHttp 登录成功");
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

}
