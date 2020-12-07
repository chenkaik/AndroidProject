package com.example.android.project.activity;

import android.view.View;

import com.android.lib.util.InputTextHelper;
import com.example.android.project.R;
import com.example.android.project.databinding.ActivityRegisterBinding;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private InputTextHelper mInputTextHelper;
    private ActivityRegisterBinding mActivityRegisterBinding;

    @Override
    protected void initView() {
        mActivityRegisterBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(mActivityRegisterBinding.getRoot());
        mActivityRegisterBinding.cvRegisterCountdown.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mInputTextHelper = new InputTextHelper(mActivityRegisterBinding.btnRegisterCommit);
        mInputTextHelper.addViews(mActivityRegisterBinding.etRegisterPhone, mActivityRegisterBinding.etRegisterCode, mActivityRegisterBinding.etRegisterPassword1, mActivityRegisterBinding.etRegisterPassword2);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.cv_register_countdown){
            if (mActivityRegisterBinding.etRegisterPhone.getText().toString().length() != 11) {
                // 重置验证码倒计时控件
                mActivityRegisterBinding.cvRegisterCountdown.resetState();
                showToastMessage(getResources().getString(R.string.phone_input_error));
                return;
            }
            showToastMessage(getResources().getString(R.string.countdown_code_send_succeed));
        }else if (id == R.id.btn_register_commit){
            if (mActivityRegisterBinding.etRegisterPhone.getText().toString().length() != 11) {
                showToastMessage(getResources().getString(R.string.phone_input_error));
                return;
            }

            if (!mActivityRegisterBinding.etRegisterPassword1.getText().toString().equals(mActivityRegisterBinding.etRegisterPassword2.getText().toString())) {
                showToastMessage(getResources().getString(R.string.two_password_input_error));
                return;
            }
        }
    }

    @Override
    protected void onDestroy() {
        mInputTextHelper.removeViews();
        super.onDestroy();
    }

}
