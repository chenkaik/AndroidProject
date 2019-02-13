package com.example.android.project.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.lib.util.GsonUtil;
import com.example.android.project.R;
import com.example.android.project.entity.LoginResponse;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.OnClick;
import commom.android.http.config.UserConfig;
import commom.android.http.okhttp.CommonOkHttpResponse;
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
                String url = "https://www.shjacf.com/server/login";
                Map<String, String> params = new HashMap<>();
                params.put("username", "lixiangbin");
                params.put("password", "shjacf");
                NetWorkRequest.getInstance()
                        .post()
                        .url(url)
                        .jsonParams(new JSONObject(params).toString())
                        .tag(this)
                        .enqueue(1, this);
                break;
            case R.id.btn_other_ok:
                showToastMessage("ok_other");
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
            default:
                break;
        }
    }

    @Override
    public void onDataError(int requestCode, int responseCode, String message, boolean isOverdue) {
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
