package com.example.android.project.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.lib.Logger;
import com.example.android.project.R;

/**
 * date: 2019/3/8
 * desc: 个人中心
 */
public class UserFragment extends BaseFragment {

    private static final String TAG = "UserFragment";
    private boolean initDataSuccess; // 初始化数据是否加载成功
    private boolean initLayoutSuccess; // 布局控件是否初始化完成

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.e(TAG, "onCreate: ");
    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_user, container, false);
//        Logger.e(TAG, "onCreateView: " );
//        return rootView;
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        initLayoutSuccess = true;
        initLoadData();
    }

    private void initLoadData() {
        if (getUserVisibleHint() && initLayoutSuccess && !initDataSuccess) {
            loadData();
        } else {
            if (initDataSuccess) { // 主要用于点击切换回来更新某一接口的数据
                Logger.e(TAG, "initLoadData: 调用了");
            }
        }
    }

    private void loadData() {
        initDataSuccess = true;
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

}
