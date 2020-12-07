package com.example.android.project.activity;

import android.view.View;
import android.widget.Toast;

import com.example.android.project.R;
import com.example.android.project.databinding.ActivityStatusBinding;
import com.example.android.project.util.LoadStatusView;

public class StatusActivity extends BaseActivity implements LoadStatusView.OnRefreshListener, View.OnClickListener {

    private LoadStatusView mLoadStatusView;

    private ActivityStatusBinding mActivityStatusBinding;


    @Override
    protected void initView() {
        mActivityStatusBinding = ActivityStatusBinding.inflate(getLayoutInflater());
        setContentView(mActivityStatusBinding.getRoot());
        mActivityStatusBinding.commonHead.navigationBar.setTitle("页面状态");
        mLoadStatusView = mActivityStatusBinding.loadStatusView;
        mLoadStatusView.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
        mActivityStatusBinding.btnLoad.setOnClickListener(this);
        mActivityStatusBinding.btnNoNet.setOnClickListener(this);
        mActivityStatusBinding.btnLoadingFail.setOnClickListener(this);
        mActivityStatusBinding.btnLoadingFinish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_load){
            mLoadStatusView.setLoading();
        }else if (id == R.id.btn_no_net){
            mLoadStatusView.setNoNet();
        }else if (id == R.id.btn_loading_fail){
            mLoadStatusView.setFailRefresh();
        }else if (id == R.id.btn_loading_finish){
            mLoadStatusView.setHide();
        }else {

        }
    }

    @Override
    public void onRefreshListener() {
        mLoadStatusView.setLoading();
        Toast.makeText(this, "重新加载了", Toast.LENGTH_SHORT).show();
    }

}
