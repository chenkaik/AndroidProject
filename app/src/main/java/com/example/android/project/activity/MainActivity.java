package com.example.android.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.lib.Logger;
import com.android.lib.util.ScreenManager;
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

    @OnClick({R.id.btn_retrofit, R.id.btn_okhttp, R.id.btn_other, R.id.btn_BottomNavigationView})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_retrofit:
//                showToastMessage("retrofit");
                Logger.e(TAG, "onClick: retrofit");
                Intent intent = new Intent(this, RetrofitTestActivity.class);
                ScreenManager.getScreenManager().startPage(this, intent, true);
                break;
            case R.id.btn_okhttp:
                Intent intentOk = new Intent(this, OkHttpTestActivity.class);
                ScreenManager.getScreenManager().startPage(this, intentOk, true);
                Logger.e(TAG, "onClick: okhttp");
                break;
            case R.id.btn_other:
                Intent intentOt = new Intent(this, TestActivity.class);
                ScreenManager.getScreenManager().startPage(this, intentOt, true);
                break;
            case R.id.btn_BottomNavigationView:
                Intent myIntent = new Intent(this, MyActivity.class);
                ScreenManager.getScreenManager().startPage(this, myIntent, true);
                break;
            default:
                break;
        }
    }

}
