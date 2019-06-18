package com.example.android.project.activity;

import android.content.Intent;
import android.view.View;

import com.android.lib.util.CacheDataManager;
import com.android.lib.util.ImageLoader;
import com.android.lib.util.ScreenManager;
import com.android.lib.widget.NavigationBar;
import com.example.android.project.R;
import com.example.android.project.widget.SettingBar;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.navigationBar)
    NavigationBar mNavigationBar;

    @BindView(R.id.sb_setting_cache)
    SettingBar mCleanCacheView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        mNavigationBar.setTitle(getResources().getString(R.string.setting_title));
    }

    @Override
    protected void initData() {
        // 获取应用缓存大小
        mCleanCacheView.setRightText(CacheDataManager.getTotalCacheSize(this));
    }

    @OnClick({R.id.sb_common, R.id.sb_setting_update, R.id.sb_setting_agreement
            , R.id.sb_setting_about, R.id.sb_setting_cache, R.id.btn_setting_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sb_common:
                break;
            case R.id.sb_setting_update: // 检测更新
                break;
            case R.id.sb_setting_agreement: // 隐私协议
                break;
            case R.id.sb_setting_about: // 关于我们
                Intent intent = new Intent(this, AboutActivity.class);
                ScreenManager.getScreenManager().startPage(this, intent, true);
                break;
            case R.id.sb_setting_cache: // 清除缓存
                ImageLoader.clearMemory(this);
                CacheDataManager.clearAllCache(this);
                // 重新获取应用缓存大小
                mCleanCacheView.setRightText(CacheDataManager.getTotalCacheSize(this));
                break;
            case R.id.btn_setting_exit: // 退出登录
                break;
            default:
                break;
        }
    }

}
