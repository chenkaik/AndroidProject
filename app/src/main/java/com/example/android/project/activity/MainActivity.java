package com.example.android.project.activity;

import android.content.Intent;
import android.view.View;

import com.android.lib.Logger;
import com.android.lib.util.ScreenManager;
import com.example.android.project.R;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    // 记录退出按下的时间
//    private long exitTime = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_retrofit, R.id.btn_okhttp, R.id.btn_other,
            R.id.btn_BottomNavigationView, R.id.btn_status})
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
            case R.id.btn_status:
                Intent statusIntent = new Intent(this, StatusActivity.class);
                ScreenManager.getScreenManager().startPage(this, statusIntent, true);
                break;
            default:
                break;
        }
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
//            if ((System.currentTimeMillis() - exitTime) > 2000) {
//                showToastMessage(getResources().getString(R.string.home_exit_hint));
//                exitTime = System.currentTimeMillis();
//            } else {
//                ScreenManager.getScreenManager().killAllActivity();
//                finish();
////                android.os.Process.killProcess(android.os.Process.myPid());
//                //System.exit(0);
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

}
