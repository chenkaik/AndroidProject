package com.example.android.project.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import com.android.lib.util.ScreenManager;
import com.android.lib.util.SystemInfo;
import com.example.android.project.databinding.ActivityFirstBinding;

public class FirstActivity extends BaseActivity {

    private MyHandler myhandler;

    @Override
    protected void initView() {
        // 这是一种全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 适配刘海屏
        if (SystemInfo.hasP()) {
//            requestWindowFeature(Window.FEATURE_NO_TITLE);
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            WindowManager.LayoutParams lp = this.getWindow().getAttributes();
//            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
//            this.getWindow().setAttributes(lp);
//            DisplayCutoutDemo displayCutoutDemo = new DisplayCutoutDemo(this);
//            displayCutoutDemo.openFullScreenModel();
            // next
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            // 仅当缺口区域完全包含在状态栏之中时，才允许窗口延伸到刘海区域显示
//            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT;
            // 永远不允许窗口延伸到刘海区域
//            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER;
            // 始终允许窗口延伸到屏幕短边上的刘海区域
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(lp);
        } else {
            // 9.0以下适配
        }
        setContentView(ActivityFirstBinding.inflate(getLayoutInflater()).getRoot());
        // 5.0以上的os才支持 即Api 21以上 android:fitsSystemWindows=”true”
//        if (SystemVersionUtil.hasLollipop()) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
        myhandler = new MyHandler();
        myhandler.sendEmptyMessageDelayed(2, 2000L);
    }

    @Override
    protected void initData() {

    }

    @SuppressLint("HandlerLeak")
    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 2){
                Intent intent = new Intent(FirstActivity.this, LoginActivity.class);
                ScreenManager.getScreenManager().startPage(FirstActivity.this, intent, false);
            }
            super.handleMessage(msg);
        }
    }

    @Override
    protected void onDestroy() {
        myhandler = null;
        super.onDestroy();
    }
}
