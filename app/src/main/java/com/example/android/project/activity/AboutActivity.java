package com.example.android.project.activity;

import com.example.android.project.R;
import com.example.android.project.databinding.ActivityAboutBinding;
import com.example.android.project.databinding.CommonHeadLayoutBinding;

public class AboutActivity extends BaseActivity {

    private CommonHeadLayoutBinding mCommonHeadLayoutBinding;

    @Override
    protected void initView() {
        ActivityAboutBinding activityAboutBinding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(activityAboutBinding.getRoot());
        mCommonHeadLayoutBinding = activityAboutBinding.commonHead;
        mCommonHeadLayoutBinding.navigationBar.setTitle(getResources().getString(R.string.about_title));
    }

    @Override
    protected void initData() {

    }

}
