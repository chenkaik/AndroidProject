package com.example.android.project.activity;

import android.os.Bundle;
import android.view.View;

import com.example.android.project.R;

import butterknife.OnClick;

public class OkHttpTestActivity extends BaseActivity {

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
                showToastMessage("ok");
                break;
            case R.id.btn_other_ok:
                showToastMessage("ok_other");
                break;
            default:
                break;
        }
    }

}
