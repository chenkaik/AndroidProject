package com.example.android.project.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date: 2019/3/26
 * desc: 基类
 */
public abstract class BaseFragment extends Fragment {

    private Unbinder mButterKnife; // View注解

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        mButterKnife = ButterKnife.bind(this, rootView);
        initView();
        initData();
        return rootView;
    }

    // 引入布局
    protected abstract int getLayoutId();

    // 初始化控件
    protected abstract void initView();

    // 初始化数据
    protected abstract void initData();

    @Override
    public void onDestroy() {
        if (mButterKnife != null) {
            mButterKnife.unbind();
        }
        super.onDestroy();
    }

}
