package com.example.android.project.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.lib.Logger;
import com.android.lib.util.ScreenManager;
import com.example.android.project.R;
import com.example.android.project.activity.LoginActivity;
import com.example.android.project.activity.MyActivity;

import butterknife.OnClick;

/**
 * date: 2019/3/8
 * desc: 首页
 */
public class HomeFragment extends BaseFragment {

    private static final String TAG = "HomeFragment";
    private boolean mInitData; // 初始化数据是否加载成功
    private boolean mInitLayout; // 布局控件是否初始化完成
    private MyActivity mActivity;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onAttach(Context context) {
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

    @OnClick({R.id.btn_to_login_page})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_to_login_page:
                Intent intent = new Intent(getMyActivity(), LoginActivity.class);
                ScreenManager.getScreenManager().startPage(getMyActivity(), intent, true);
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
