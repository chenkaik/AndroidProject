package com.example.android.project.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.lib.Logger;
import com.example.android.project.activity.MainActivity;
import com.example.android.project.databinding.FragmentUserBinding;

import org.jetbrains.annotations.NotNull;

/**
 * date: 2019/3/8
 * desc: 个人中心
 */
public class UserFragment extends BaseFragment {

    private static final String TAG = "UserFragment";
    private MainActivity mActivity;

    public static UserFragment newInstance() {
        return new UserFragment();
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
    protected View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentUserBinding fragmentUserBinding = FragmentUserBinding.inflate(inflater, container, false);
        return fragmentUserBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.e(TAG, "onActivityCreated: ");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
//        mInitLayout = true;
//        initLoadData();
    }

    @Override
    protected void loadData() {
        Toast.makeText(getMyActivity(), "用户第一次加载", Toast.LENGTH_SHORT).show();
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logger.e(TAG, "setUserVisibleHint: 执行了" + isVisibleToUser);
//        if (isVisibleToUser) {
//            initLoadData();
//        }
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
