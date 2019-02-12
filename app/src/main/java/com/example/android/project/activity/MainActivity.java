package com.example.android.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.lib.Logger;
import com.example.android.project.R;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.btn_retrofit, R.id.btn_okhttp})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_retrofit:
//                showToastMessage("retrofit");
                Logger.e(TAG, "onClick: retrofit");
                startActivity(new Intent(this, RetrofitTestActivity.class));
                break;
            case R.id.btn_okhttp:
                showToastMessage("okhttp");
                Logger.e(TAG, "onClick: okhttp");
                break;
            default:
                break;
        }
    }

}
