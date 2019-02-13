package com.example.android.project.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.lib.util.GsonUtil;
import com.example.android.project.R;
import com.example.android.project.entity.LoginResponse;
import com.example.android.project.entity.TradeNumberResponse;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.OnClick;
import commom.android.http.config.UserConfig;
import commom.android.http.response.CommonOkHttpResponse;
import commom.android.http.retrofit.NetWorkRequest;

public class OkHttpTestActivity extends BaseActivity implements CommonOkHttpResponse {

    private static final String TAG = "OkHttpTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ok_http_test;
    }

    @OnClick({R.id.btn_login_ok, R.id.btn_other_ok})
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
            default:
                break;
        }
    }

//    @Override
//    public void onSuccess(int requestCode, Response response) {
//
//    }

    @Override
    public void onDataReady(int requestCode, Object object, String json) {
        dismissLoadingDialog();
        Log.e(TAG, "onDataReady: " + json);
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
    public void onDataError(int requestCode, int responseCode, String message, boolean isOverdue) {
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