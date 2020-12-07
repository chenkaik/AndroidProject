package com.example.android.project.activity;

import android.content.Intent;
import android.view.View;

import com.android.lib.Logger;
import com.android.lib.util.CacheDataManager;
import com.android.lib.util.ImageLoader;
import com.android.lib.util.ScreenManager;
import com.example.android.project.R;
import com.example.android.project.databinding.ActivitySettingBinding;
import com.example.android.project.widget.SettingBar;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "SettingActivity";
    private SettingBar mCleanCacheView;

    private ActivitySettingBinding mActivitySettingBinding;


    @Override
    protected void initView() {
        mActivitySettingBinding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(mActivitySettingBinding.getRoot());
        mActivitySettingBinding.commonHead.navigationBar.setTitle(getResources().getString(R.string.setting_title));
        mActivitySettingBinding.sbCommon.setOnClickListener(this);
        mActivitySettingBinding.sbSettingUpdate.setOnClickListener(this);
        mActivitySettingBinding.sbSettingAgreement.setOnClickListener(this);
        mActivitySettingBinding.sbSettingAbout.setOnClickListener(this);
        mActivitySettingBinding.btnSettingExit.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mCleanCacheView = mActivitySettingBinding.sbSettingCache;
        mCleanCacheView.setOnClickListener(this);
        // 获取应用缓存大小
        mCleanCacheView.setRightText(CacheDataManager.getTotalCacheSize(this));
    }

    //    @OnClick({R.id.sb_common, R.id.sb_setting_update, R.id.sb_setting_agreement
//            , R.id.sb_setting_about, R.id.sb_setting_cache, R.id.btn_setting_exit})
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.sb_common) {
            Logger.e(TAG, "onClick: test");
        } else if (id == R.id.sb_setting_update) {
            // 检测更新
        } else if (id == R.id.sb_setting_agreement) {
            // 隐私协议
        } else if (id == R.id.sb_setting_about) { // 关于我们
            Intent intent = new Intent(this, AboutActivity.class);
            ScreenManager.getScreenManager().startPage(this, intent, true);
        } else if (id == R.id.sb_setting_cache) {
            ImageLoader.clearMemory(this);
            CacheDataManager.clearAllCache(this);
            // 重新获取应用缓存大小
            mCleanCacheView.setRightText(CacheDataManager.getTotalCacheSize(this));
        } else {
            // 退出登录
        }
    }

}
