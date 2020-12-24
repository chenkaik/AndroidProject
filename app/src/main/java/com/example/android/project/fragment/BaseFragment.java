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

    private boolean isOk = false; // 是否完成View初始化
    private boolean isFirst = true; // 是否为第一次加载

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getLayoutView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        isOk = true; // 完成initView后改变view的初始化状态为完成
    }

    @Override
    public void onResume() {
        super.onResume();
        initLoadData(); // 在onResume中进行数据懒加载
    }

    private void initLoadData() {
        if (isOk && isFirst) { // 加载数据时判断是否完成view的初始化，以及是不是第一次加载此数据
            loadData();
            isFirst = false; // 加载第一次数据后改变状态，后续不再重复加载
        }
    }

    // 引入布局
    protected abstract View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    // 初始化控件
    protected abstract void initView();

    // 初始化数据
    protected abstract void initData();

    // 子fragment实现懒加载的方法
    protected abstract void loadData();

}
