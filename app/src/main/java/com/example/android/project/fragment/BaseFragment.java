package com.example.android.project.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date: 2019/3/26
 * desc: 基类
 */
public abstract class BaseFragment extends Fragment {

    private Unbinder mButterKnife; // View注解
    private boolean isFirstLoad = true; // 是否第一次加载

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
        if (mButterKnife != null) {
            mButterKnife.unbind();
        }
    }

//    @Override
//    public void onDestroy() {
//        if (mButterKnife != null) {
//            mButterKnife.unbind();
//        }
//        super.onDestroy();
//    }

}
