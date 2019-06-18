package com.example.android.project.activity;

import com.android.lib.widget.NavigationBar;
import com.example.android.project.R;

import butterknife.BindView;

public class AboutActivity extends BaseActivity {

    @BindView(R.id.navigationBar)
    NavigationBar mNavigationBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {
        mNavigationBar.setTitle(getResources().getString(R.string.about_title));
    }

    @Override
    protected void initData() {

    }

}
