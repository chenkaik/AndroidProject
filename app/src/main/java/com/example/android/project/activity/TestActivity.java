package com.example.android.project.activity;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.lib.adapter.BaseRecyclerViewAdapter;
import com.android.lib.widget.NavigationBar;
import com.android.lib.widget.XRecyclerView;
import com.example.android.project.R;
import com.example.android.project.adapter.FootAdapter;
import com.example.android.project.adapter.HeadAdapter;
import com.example.android.project.adapter.TestAdapter;
import com.example.android.project.entity.Test;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TestActivity extends BaseActivity implements BaseRecyclerViewAdapter.OnItemClickListener, BaseRecyclerViewAdapter.OnItemLongClickListener, BaseRecyclerViewAdapter.OnViewClickListener {

    @BindView(R.id.navigationBar)
    NavigationBar mNavigationBar;
    @BindView(R.id.x_RecyclerView)
    XRecyclerView mRecyclerView;
    private List<Test> list = new ArrayList<>();
    private TestAdapter testAdapter;
    private RecyclerView mHeadRecyclerView;
    private RecyclerView mFootRecyclerView;
    private List<Test> mHeadList = new ArrayList<>();
    private List<Test> mFootList = new ArrayList<>();
    private HeadAdapter mHeadAdapter;
    private FootAdapter mFootAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other;
    }

    @Override
    protected void initView() {
        mNavigationBar.setTitle("测试");
        for (int i = 0; i < 50; i++) {
            list.add(new Test("测试" + i));
        }

        for (int i = 0; i < 10; i++) {
            mHeadList.add(new Test("上面" + i));
        }

        for (int i = 0; i < 10; i++) {
            mFootList.add(new Test("下面" + i));
        }
    }

    @Override
    protected void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        testAdapter = new TestAdapter(this, list, this);
        @SuppressLint("InflateParams") View headView = LayoutInflater.from(this).inflate(R.layout.head_layout, null);
        mHeadRecyclerView = headView.findViewById(R.id.head_RecyclerView);
        mHeadRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHeadAdapter = new HeadAdapter(this, mHeadList);
        mHeadRecyclerView.setAdapter(mHeadAdapter);
        @SuppressLint("InflateParams") View footView = LayoutInflater.from(this).inflate(R.layout.foot_layout, null);
        mFootRecyclerView = footView.findViewById(R.id.foot_RecyclerView);
        mFootRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFootAdapter = new FootAdapter(this, mFootList);
        mFootRecyclerView.setAdapter(mFootAdapter);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        headView.setLayoutParams(params);
        footView.setLayoutParams(params);
        mRecyclerView.addHeaderView(headView);
        mRecyclerView.addFootView(footView);
        mRecyclerView.setAdapter(testAdapter);
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
        }, v -> showToastMessage("点击了取消"));
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

    @Override
    protected void onDestroy() {
        list.clear();
        list = null;
        testAdapter = null;
        mHeadList.clear();
        mHeadList = null;
        mHeadAdapter = null;
        mFootList.clear();
        mFootList = null;
        mFootAdapter = null;
        super.onDestroy();
    }
}
