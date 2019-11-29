package com.example.android.project.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.android.lib.Logger;
import com.android.lib.util.ScreenManager;
import com.android.lib.widget.NavigationBar;
import com.example.android.project.R;
import com.example.android.project.activity.LoginActivity;
import com.example.android.project.activity.MyActivity;
import com.example.android.project.activity.OkHttpTestActivity;
import com.example.android.project.activity.RegisterActivity;
import com.example.android.project.activity.RetrofitTestActivity;
import com.example.android.project.activity.SettingActivity;
import com.example.android.project.activity.StatusActivity;
import com.example.android.project.activity.TestActivity;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * date: 2019/3/8
 * desc: 首页
 */
public class HomeFragment extends BaseFragment {

    private static final String TAG = "HomeFragment";
    @BindView(R.id.navigationBar)
    NavigationBar mNavigationBar;
    private boolean mInitData; // 初始化数据是否加载成功
    private boolean mInitLayout; // 布局控件是否初始化完成
    private MyActivity mActivity;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mActivity = (MyActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.e(TAG, "onCreate: ");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        mNavigationBar.setTitle("首页");
    }

    @Override
    protected void initData() {
        mInitLayout = true;
        initLoadData();
    }

    private void initLoadData() {
        if (getUserVisibleHint() && mInitLayout && !mInitData) {
            loadData();
        } else {
            if (mInitData) { // 主要用于点击切换回来更新某一接口的数据
                Logger.e(TAG, "initLoadData: 调用了");
            }
        }
    }

    private void loadData() {
        mInitData = true;
        Logger.e(TAG, "loadData: " + "加载数据");
    }

    /**
     * onAttach()之前，调用setUserVisibleHint(false)。
     * onCreateView()之前，如果该界面为当前页，则调用setUserVisibleHint(true)，否则调用setUserVisibleHint(false)。
     * 界面变为可见时，调用setUserVisibleHint(true)。
     * 界面变为不可见时，调用setUserVisibleHint(false)。
     *
     * @param isVisibleToUser 是否可见的
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logger.e(TAG, "setUserVisibleHint: 执行了" + isVisibleToUser + " -- " + mInitLayout);
        if (isVisibleToUser) {
            initLoadData();
        }
    }

    @OnClick({R.id.btn_to_login_page, R.id.btn_to_register_page,R.id.btn_retrofit, R.id.btn_okhttp, R.id.btn_other,
            R.id.btn_BottomNavigationView, R.id.btn_status, R.id.btn_to_setting_page})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_to_login_page:
                Intent intent = new Intent(getMyActivity(), LoginActivity.class);
                ScreenManager.getScreenManager().startPage(getMyActivity(), intent, true);
                break;
            case R.id.btn_to_register_page:
                Intent registerIntent = new Intent(getMyActivity(), RegisterActivity.class);
                ScreenManager.getScreenManager().startPage(getMyActivity(), registerIntent, true);
                break;
            case R.id.btn_retrofit:
//                showToastMessage("retrofit");
                Logger.e(TAG, "onClick: retrofit");
                Intent intentNetWork = new Intent(getMyActivity(), RetrofitTestActivity.class);
                ScreenManager.getScreenManager().startPage(getMyActivity(), intentNetWork, true);
                break;
            case R.id.btn_okhttp:
                Intent intentOk = new Intent(getMyActivity(), OkHttpTestActivity.class);
                ScreenManager.getScreenManager().startPage(getMyActivity(), intentOk, true);
                Logger.e(TAG, "onClick: okhttp");
                break;
            case R.id.btn_other:
                Intent intentOt = new Intent(getMyActivity(), TestActivity.class);
                ScreenManager.getScreenManager().startPage(getMyActivity(), intentOt, true);
                break;
            case R.id.btn_BottomNavigationView:
                Intent myIntent = new Intent(getMyActivity(), MyActivity.class);
                ScreenManager.getScreenManager().startPage(getMyActivity(), myIntent, true);
                break;
            case R.id.btn_status:
                Intent statusIntent = new Intent(getMyActivity(), StatusActivity.class);
                ScreenManager.getScreenManager().startPage(getMyActivity(), statusIntent, true);
                break;
            case R.id.btn_to_setting_page:
                Intent settingIntent = new Intent(getMyActivity(), SettingActivity.class);
                ScreenManager.getScreenManager().startPage(getMyActivity(), settingIntent, true);
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.e(TAG, "onResume: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.e(TAG, "onDestroy: ");
    }

    private MyActivity getMyActivity() {
        return mActivity == null ? (MyActivity) getActivity() : mActivity;
    }

}
