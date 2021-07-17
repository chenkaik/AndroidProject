package com.example.android.project.activity;

import android.content.Intent;
import android.view.View;

import com.android.lib.util.InputTextHelper;
import com.android.lib.widget.NavigationBar;
import com.example.android.project.R;
import com.example.android.project.databinding.ActivityLoginBinding;
import com.example.android.project.databinding.CommonHeadLayoutBinding;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private InputTextHelper mInputTextHelper;
    private ActivityLoginBinding mActivityLoginBinding;

    @Override
    protected void initView() {
        mActivityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mActivityLoginBinding.getRoot());
        CommonHeadLayoutBinding commonHeadLayoutBinding = mActivityLoginBinding.commonHead;
        NavigationBar navigationBar = commonHeadLayoutBinding.navigationBar;
        navigationBar.hideLeftLayout();
        navigationBar.setTitle(getResources().getString(R.string.login_text));

//        mCommitView = mActivityLoginBinding.btnLoginCommit;
        mInputTextHelper = new InputTextHelper(mActivityLoginBinding.btnLoginCommit);
        mInputTextHelper.addViews(mActivityLoginBinding.etLoginPhone, mActivityLoginBinding.etLoginPassword);
        mActivityLoginBinding.tvLoginForget.setOnClickListener(this);
        mActivityLoginBinding.btnLoginCommit.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mActivityLoginBinding.etLoginPhone.setText("123");
        mActivityLoginBinding.etLoginPassword.setText("123");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_login_forget) {
            showToastMessage("忘记密码");
        } else if (id == R.id.btn_login_commit) {
//                if (mPhoneView.getText().toString().length() != 11) {
//                    showToastMessage("手机号输入不正确");
//                    break;
//                }
//            Intent intent = new Intent(this, MainActivity.class);
//            ScreenManager.getScreenManager().startPage(this, intent, false);
            startActivity(new Intent(this, DemoActivity.class));
        }
    }

    @Override
    protected void onDestroy() {
        mInputTextHelper.removeViews();
        super.onDestroy();
    }

}
