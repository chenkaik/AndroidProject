package com.example.android.project.activity;

import android.view.View;

import com.android.lib.Logger;
import com.example.android.project.R;
import com.example.android.project.entity.HomeIndexResponse;
import com.example.android.project.entity.LoginResponse;
import com.example.android.project.entity.request.HomeIndexRequest;
import com.example.android.project.entity.request.LoginRequest;

import butterknife.OnClick;
import commom.android.http.ApiManager;
import commom.android.http.config.UserConfig;
import commom.android.http.response.BaseResponse;
import commom.android.http.response.CommonResponse;
import commom.android.http.retrofit.NetWorkRequest;

public class RetrofitTestActivity extends BaseActivity implements CommonResponse {

    private static final String TAG = "RetrofitTestActivity";
    private LoginRequest loginRequest;
    private HomeIndexRequest request;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_retrofit_test;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        loginRequest = new LoginRequest();
        loginRequest.setUserName("lixiangbin");
        loginRequest.setPassword("shjacf");
        request = new HomeIndexRequest();
        request.setMSG_BODY(new HomeIndexRequest.MSGBODYBean());
    }

    @OnClick({R.id.btn_login, R.id.btn_other})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                UserConfig.getInstance().clearToken();
                NetWorkRequest.getRequestManager().asyncNetWork(TAG, 1, ApiManager.getInstance().getApiService().login(loginRequest), this, true);
                break;
            case R.id.btn_other:
                NetWorkRequest.getRequestManager().asyncNetWork(TAG, 100, ApiManager.getInstance().getApiService().homeIndex(request), this, true);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDataReady(BaseResponse response) {
        switch (response.requestCode) {
            case 1:
                LoginResponse loginResponseEntity = (LoginResponse) response;
                showToastMessage("成功1");
                UserConfig.getInstance().putToken(loginResponseEntity.getMSG_BODY().getToken());
                Logger.e(TAG, ":111111");
                break;
            case 100:
                HomeIndexResponse homeIndexResponse = (HomeIndexResponse) response;
                if (homeIndexResponse != null) {
                    showToastMessage("成功100");
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

    @Override
    public void showLoading(String msg) {
        showLoadingDialog(msg);
    }

    @Override
    public void dismissLoading() {
        dismissLoadingDialog();
    }

}
