package com.example.android.project.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.lib.Logger;
import com.example.android.project.R;
import com.example.android.project.activity.MyActivity;

/**
 * @date: 2019/4/12
 * @describe: 发现模块
 */
public class FindFragment extends BaseFragment {

    private static final String TAG = "FindFragment";
    private boolean initIsSuccess; // 初始化数据是否加载成功
    private boolean initIsUiPrepared; // 布局控件是否初始化完成
    private MyActivity activity;

    public static FindFragment newInstance() {
        return new FindFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MyActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.e(TAG, "onCreate: ");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        initIsUiPrepared = true;
        initLoadData();
    }

    private void initLoadData() {
        if (getUserVisibleHint() && initIsUiPrepared && !initIsSuccess) {
            loadData();
        } else {
            if (initIsSuccess) { // 主要用于点击切换回来更新某一接口的数据
                Logger.e(TAG, "initLoadData: 调用了");
            }
        }
    }

    private void loadData() {
        initIsSuccess = true;
        Logger.e(TAG, "loadData: " + "加载数据");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logger.e(TAG, "setUserVisibleHint: 执行了" + isVisibleToUser);
        if (isVisibleToUser) {
            initLoadData();
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
        if (activity != null) {
            return activity;
        } else {
            return (MyActivity) getActivity();
        }
    }

}
