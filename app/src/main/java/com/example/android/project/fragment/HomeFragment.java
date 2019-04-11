package com.example.android.project.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.android.lib.Logger;
import com.android.lib.util.ScreenManager;
import com.example.android.project.R;
import com.example.android.project.activity.MyActivity;
import com.example.android.project.activity.TestActivity;

import butterknife.BindView;

/**
 * @date: 2019/3/8
 * @describe: 首页
 */
public class HomeFragment extends BaseFragment {

    private static final String TAG = "HomeFragment";
    private boolean initIsSuccess; // 初始化数据是否加载成功
    private boolean initIsUiPrepared; // 布局控件是否初始化完成
    private MyActivity activity;
    @BindView(R.id.tv_home)
    TextView textView;

    public static HomeFragment newInstance() {
        return new HomeFragment();
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
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, TestActivity.class);
                ScreenManager.getScreenManager().startPage(activity, intent, true);
            }
        });
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

    /**
     * onAttach()之前，调用setUserVisibleHint(false)。
     * onCreateView()之前，如果该界面为当前页，则调用setUserVisibleHint(true)，否则调用setUserVisibleHint(false)。
     * 界面变为可见时，调用setUserVisibleHint(true)。
     * 界面变为不可见时，调用setUserVisibleHint(false)。
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logger.e(TAG, "setUserVisibleHint: 执行了" + isVisibleToUser + " -- " + initIsUiPrepared);
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
