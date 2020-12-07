package com.example.android.project.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.lib.Logger;
import com.android.lib.util.ScreenManager;
import com.example.android.project.R;
import com.example.android.project.activity.MainActivity;
import com.example.android.project.activity.NetWorkActivity;
import com.example.android.project.activity.RegisterActivity;
import com.example.android.project.activity.SettingActivity;
import com.example.android.project.activity.StatusActivity;
import com.example.android.project.activity.TestActivity;
import com.example.android.project.databinding.CommonHeadLayoutBinding;
import com.example.android.project.databinding.FragmentHomeBinding;

import org.jetbrains.annotations.NotNull;

/**
 * date: 2019/3/8
 * desc: 首页
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "HomeFragment";
    private MainActivity mActivity;
    private FragmentHomeBinding mFragmentHomeBinding;
    private CommonHeadLayoutBinding mCommonHeadLayoutBinding;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
        Logger.e(TAG, "onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.e(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.e(TAG, "onCreateView: ");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.e(TAG, "onActivityCreated: ");
    }

    @Override
    protected View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        mFragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        mCommonHeadLayoutBinding = mFragmentHomeBinding.commonHead;
        return mFragmentHomeBinding.getRoot();
    }

    @Override
    protected void initView() {
        mCommonHeadLayoutBinding.navigationBar.setTitle("首页");
        mCommonHeadLayoutBinding.navigationBar.hideLeftLayout();
        mFragmentHomeBinding.btnToRegisterPage.setOnClickListener(this);
        mFragmentHomeBinding.btnNetwork.setOnClickListener(this);
        mFragmentHomeBinding.btnOther.setOnClickListener(this);
        mFragmentHomeBinding.btnStatus.setOnClickListener(this);
        mFragmentHomeBinding.btnToSettingPage.setOnClickListener(this);
    }

    @Override
    protected void initData() {
//        mInitLayout = true;
//        initLoadData();
    }

//    private void initLoadData() {
//        if (getUserVisibleHint() && mInitLayout && !mInitData) {
//            loadData();
//        } else {
//            if (mInitData) { // 主要用于点击切换回来更新某一接口的数据
//                Logger.e(TAG, "initLoadData: 调用了");
//            }
//        }
//    }
//
//    private void loadData() {
//        mInitData = true;
//        Logger.e(TAG, "loadData: " + "加载数据");
//    }

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
//        Logger.e(TAG, "setUserVisibleHint: 执行了" + isVisibleToUser + " -- " + mInitLayout);
//        if (isVisibleToUser) {
//            initLoadData();
//        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_to_register_page) {
            Intent registerIntent = new Intent(getMyActivity(), RegisterActivity.class);
            ScreenManager.getScreenManager().startPage(getMyActivity(), registerIntent, true);
        } else if (id == R.id.btn_network) {
            Intent networkIntent = new Intent(getMyActivity(), NetWorkActivity.class);
            ScreenManager.getScreenManager().startPage(getMyActivity(), networkIntent, true);
        } else if (id == R.id.btn_other) {
            Intent intentOt = new Intent(getMyActivity(), TestActivity.class);
            ScreenManager.getScreenManager().startPage(getMyActivity(), intentOt, true);
        } else if (id == R.id.btn_status) {
            Intent statusIntent = new Intent(getMyActivity(), StatusActivity.class);
            ScreenManager.getScreenManager().startPage(getMyActivity(), statusIntent, true);
        } else {
            Intent settingIntent = new Intent(getMyActivity(), SettingActivity.class);
            ScreenManager.getScreenManager().startPage(getMyActivity(), settingIntent, true);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.e(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.e(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.e(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.e(TAG, "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.e(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.e(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.e(TAG, "onDetach: ");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Logger.e(TAG, "onHiddenChanged= " + hidden);
    }

    private MainActivity getMyActivity() {
        return mActivity == null ? (MainActivity) getActivity() : mActivity;
    }

}
