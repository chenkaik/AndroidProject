package com.example.android.project;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.android.lib.Logger;
import com.android.lib.util.CrashHandler;

import common.android.https.ApiManager;
import common.android.https.config.HttpConfig;

/**
 * date: 2019/1/30
 * desc: 程序入口
 */
public class CommonApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        // 获取全局的Context
        sContext = getApplicationContext();
        // 是否打印输出日志
        Logger.LOG_ENABLE = true;
        // 初始化接口请求
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