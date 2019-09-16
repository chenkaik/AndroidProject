package com.example.android.project;

import android.app.Application;
import android.content.Context;

import com.android.lib.util.CrashHandler;

import common.android.https.ApiManager;
import common.android.https.config.HttpConfig;

/**
 * date: 2019/1/30
 * desc: 程序入口
 */
public class CommonApplication extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        sContext = getApplicationContext();
        ApiManager.getInstance().init(HttpConfig.BASE_URL);
        // 捕获程序出现异常的信息
        CrashHandler.getInstance().init(sContext);
    }

    /**
     * 获取全局的上下文引用
     *
     * @return 上下文
     */
    public static Context getContext() {
        return sContext;
    }

}