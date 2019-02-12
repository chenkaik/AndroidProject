package com.example.android.project;

import android.app.Application;
import android.content.Context;

import commom.android.http.ApiManager;
import commom.android.http.config.HttpConfig;

/**
 * @date: 2019/1/30
 * @describe: 程序入口
 */
public class CommonApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        context = getApplicationContext();
        ApiManager.getInstance().init(HttpConfig.BASE_URL);
    }

    /**
     * 获取全局的上下文引用
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }

}