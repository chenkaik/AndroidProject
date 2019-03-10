package com.example.android.project.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.Logger;
import com.android.lib.util.ScreenManager;
import com.example.android.project.R;
import com.example.android.project.activity.TestActivity;

/**
 * @date: 2019/3/8
 * @describe: 首页
 */
public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.e(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        Logger.e(TAG, "onCreateView: ");
        rootView.findViewById(R.id.tv_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TestActivity.class);
                ScreenManager.getScreenManager().startPage(getActivity(), intent, true);
            }
        });
        return rootView;
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
