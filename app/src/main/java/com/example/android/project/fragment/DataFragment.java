package com.example.android.project.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.lib.Logger;
import com.example.android.project.R;

/**
 * date: 2019/3/8
 * desc: 数据
 */
public class DataFragment extends BaseFragment {

    private static final String TAG = "DataFragment";
    private boolean initIsSuccess; // 初始化数据是否加载成功
    private boolean initIsUiPrepared; // 布局控件是否初始化完成

    public static DataFragment newInstance() {
        return new DataFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.e(TAG, "onCreate: " );
    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_data, container, false);
//        Logger.e(TAG, "onCreateView: " );
//        return rootView;
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_data;
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
        Logger.e(TAG, "onResume: " );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.e(TAG, "onDestroy: " );
    }

}
