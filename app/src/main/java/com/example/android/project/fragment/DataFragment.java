package com.example.android.project.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.lib.Logger;
import com.example.android.project.R;

/**
 * @date: 2019/3/8
 * @describe: 数据
 */
public class DataFragment extends BaseFragment {

    private static final String TAG = "DataFragment";

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
