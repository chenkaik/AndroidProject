package com.example.android.project.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.lib.util.InputTextHelper;
import com.android.lib.util.ScreenManager;
import com.android.lib.widget.NavigationBar;
import com.example.android.project.R;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.navigationBar)
    NavigationBar mNavigationBar;
    @BindView(R.id.et_login_phone)
    EditText mPhoneView;
    @BindView(R.id.et_login_password)
    EditText mPasswordView;
    @BindView(R.id.btn_login_commit)
    Button mCommitView;

    private InputTextHelper mInputTextHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mNavigationBar.hideLeftLayout();
        mNavigationBar.setTitle(getResources().getString(R.string.login_text));
        mInputTextHelper = new InputTextHelper(mCommitView);
        mInputTextHelper.addViews(mPhoneView, mPasswordView);
    }

    @Override
    protected void initData() {
        mPhoneView.setText("123");
        mPasswordView.setText("123");
    }

    @OnClick({R.id.tv_login_forget, R.id.btn_login_commit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_forget:
                showToastMessage("忘记密码");
                break;
            case R.id.btn_login_commit:
//                if (mPhoneView.getText().toString().length() != 11) {
//                    showToastMessage("手机号输入不正确");
//                    break;
//                }
                Intent intent = new Intent(this, MainActivity.class);
                ScreenManager.getScreenManager().startPage(this, intent, false);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        mInputTextHelper.removeViews();
        super.onDestroy();
    }

}
