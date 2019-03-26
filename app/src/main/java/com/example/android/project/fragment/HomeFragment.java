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
