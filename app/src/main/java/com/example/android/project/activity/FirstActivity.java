package com.example.android.project.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

import com.android.lib.util.ScreenManager;
import com.example.android.project.R;
import com.example.android.project.util.DisplayCutoutDemo;

public class FirstActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        // 这是一种全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            WindowManager.LayoutParams lp = this.getWindow().getAttributes();
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            this.getWindow().setAttributes(lp);
            DisplayCutoutDemo displayCutoutDemo = new DisplayCutoutDemo(this);
            displayCutoutDemo.openFullScreenModel();
        }
        return R.layout.activity_first;
    }

    @Override
    protected void initView() {
        // 5.0以上的os才支持 即Api 21以上 android:fitsSystemWindows=”true”
//        if (SystemVersionUtil.hasLollipop()) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
        MyHandler myhandler = new MyHandler();
        myhandler.sendEmptyMessageDelayed(2, 2000L);
    }

    @Override
    protected void initData() {

    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    Intent intent = new Intent(FirstActivity.this, MainActivity.class);
                    ScreenManager.getScreenManager().startPage(FirstActivity.this, intent, false);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    }

}
