package com.example.android.project.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.lib.adapter.BaseRecyclerViewAdapter;
import com.example.android.project.R;
import com.example.android.project.adapter.TestAdapter;
import com.example.android.project.entity.Test;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TestActivity extends BaseActivity implements BaseRecyclerViewAdapter.OnItemClickListener, BaseRecyclerViewAdapter.OnItemLongClickListener, BaseRecyclerViewAdapter.OnViewClickListener {

    @BindView(R.id.test_RecyclerView)
    RecyclerView recyclerView;
    private List<Test> list = new ArrayList<>();
    private TestAdapter testAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other;
    }

    @Override
    protected void initView() {
        for (int i = 0; i < 100; i++) {
            list.add(new Test("测试" + i));
        }
    }

    @Override
    protected void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        testAdapter = new TestAdapter(this, list, this);
        recyclerView.setAdapter(testAdapter);
        testAdapter.setOnItemClickListener(this);
        testAdapter.setOnItemLongClickListener(this);
    }

    @OnClick(R.id.btn_test)
    void click(View view) {
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

    @Override
    public void onItemClick(RecyclerView.Adapter adapter, View v, int position) {
        showToastMessage("点击了 " + list.get(position).getName());
    }

    @Override
    public void onItemLongClick(RecyclerView.Adapter adapter, View v, int position) {
        showToastMessage("长按了 " + list.get(position).getName());
    }

    @Override
    public void onViewClick(int position, int type) {
        if (type == 1) {
            showToastMessage("子View点击事件 " + list.get(position).getName());
        }
    }

}
