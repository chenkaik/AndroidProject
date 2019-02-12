package com.example.android.project.activity;

import android.os.Bundle;
import android.view.View;

import com.example.android.project.R;

import butterknife.OnClick;

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other;
    }

    @OnClick(R.id.btn_test)
    void click(View view){
        showCommonAlertDialog("这是标题", "哈这是内容这是内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容哈", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToastMessage("点击了确定");
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToastMessage("点击了取消");
            }
        });
    }

}
