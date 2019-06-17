package com.example.android.project.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.lib.util.CountdownView;
import com.android.lib.util.InputTextHelper;
import com.example.android.project.R;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.et_register_phone)
    EditText mPhoneView;
    @BindView(R.id.cv_register_countdown)
    CountdownView mCountdownView;

    @BindView(R.id.et_register_code)
    EditText mCodeView;

    @BindView(R.id.et_register_password1)
    EditText mPasswordView1;
    @BindView(R.id.et_register_password2)
    EditText mPasswordView2;

    @BindView(R.id.btn_register_commit)
    Button mCommitView;

    private InputTextHelper mInputTextHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        mInputTextHelper = new InputTextHelper(mCommitView);
        mInputTextHelper.addViews(mPhoneView, mCodeView, mPasswordView1, mPasswordView2);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.cv_register_countdown, R.id.btn_register_commit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_register_countdown: // 获取验证码

                if (mPhoneView.getText().toString().length() != 11) {
                    // 重置验证码倒计时控件
                    mCountdownView.resetState();
                    showToastMessage(getResources().getString(R.string.phone_input_error));
                    break;
                }

                showToastMessage(getResources().getString(R.string.countdown_code_send_succeed));

                break;
            case R.id.btn_register_commit: // 提交注册

                if (mPhoneView.getText().toString().length() != 11) {
                    showToastMessage(getResources().getString(R.string.phone_input_error));
                    break;
                }

                if (!mPasswordView1.getText().toString().equals(mPasswordView2.getText().toString())) {
                    showToastMessage(getResources().getString(R.string.two_password_input_error));
                    break;
                }

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
