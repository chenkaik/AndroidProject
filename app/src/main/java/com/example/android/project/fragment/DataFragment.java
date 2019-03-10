package com.example.android.project.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.Logger;
import com.example.android.project.R;

/**
 * @date: 2019/3/8
 * @describe: 数据
 */
public class DataFragment extends Fragment {

    private static final String TAG = "DataFragment";

    public static DataFragment newInstance() {
        return new DataFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.e(TAG, "onCreate: " );
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_data, container, false);
        Logger.e(TAG, "onCreateView: " );
        return rootView;
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
