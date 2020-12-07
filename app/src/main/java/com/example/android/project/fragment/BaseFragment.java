package com.example.android.project.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * date: 2019/3/26
 * desc: 基类
 */
public abstract class BaseFragment extends Fragment {

    private boolean isFirstLoad = true; // 是否第一次加载

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getLayoutView(inflater, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    // 引入布局
    protected abstract View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);

    // 初始化控件
    protected abstract void initView();

    // 初始化数据
    protected abstract void initData();


    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            isFirstLoad = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFirstLoad = true;
    }

}
