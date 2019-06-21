package com.example.android.project.activity;

import android.view.View;
import android.widget.Toast;

import com.android.lib.widget.NavigationBar;
import com.example.android.project.R;
import com.example.android.project.util.LoadStatusView;

import butterknife.BindView;
import butterknife.OnClick;

public class StatusActivity extends BaseActivity implements LoadStatusView.OnRefreshListener {

    @BindView(R.id.navigationBar)
    NavigationBar mNavigationBar;

    @BindView(R.id.loadStatusView)
    LoadStatusView mLoadStatusView;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_status);
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_status;
    }

    @Override
    protected void initView() {
        mNavigationBar.setTitle("页面状态");
        mLoadStatusView.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_load, R.id.btn_no_net, R.id.btn_loading_fail,
            R.id.btn_loading_finish, R.id.loadStatusView})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_load:
                mLoadStatusView.setLoading();
                break;
            case R.id.btn_no_net:
                mLoadStatusView.setNoNet();
                break;
            case R.id.btn_loading_fail:
                mLoadStatusView.setFailRefresh();
                break;
            case R.id.btn_loading_finish:
                mLoadStatusView.setHide();
                break;
            case R.id.loadStatusView:
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefreshListener() {
        mLoadStatusView.setLoading();
        Toast.makeText(this, "重新加载了", Toast.LENGTH_SHORT).show();
    }

}
