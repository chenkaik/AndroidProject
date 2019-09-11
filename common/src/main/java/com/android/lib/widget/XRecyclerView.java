package com.android.lib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.android.lib.adapter.RecyclerWrapAdapter;

import java.util.ArrayList;

/**
 * date: 2019/1/30
 * desc: 可添加头部和底部的RecyclerView(注意调用适配器刷新数据要调本类的notifyDataSetChanged方法)
 */
public final class XRecyclerView extends RecyclerView {

    private static final ArrayList<View> mHeaderViews = new ArrayList<>();

    private static final ArrayList<View> mFootViews = new ArrayList<>();

    private Adapter mAdapter;

    public XRecyclerView(Context context) {
        super(context);
    }

    public XRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addHeaderView(View view) { // 添加Head
        mHeaderViews.clear();
        mHeaderViews.add(view);
        if (mAdapter != null) {
            if (!(mAdapter instanceof RecyclerWrapAdapter)) {
                mAdapter = new RecyclerWrapAdapter(mHeaderViews, mFootViews, mAdapter);
//                mAdapter.notifyDataSetChanged();
            }
        }
    }

    public void addFootView(View view) { // 添加Foot
        mFootViews.clear();
        mFootViews.add(view);
        if (mAdapter != null) {
            if (!(mAdapter instanceof RecyclerWrapAdapter)) {
                mAdapter = new RecyclerWrapAdapter(mHeaderViews, mFootViews, mAdapter);
//                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (mHeaderViews.isEmpty() && mFootViews.isEmpty()) {
            super.setAdapter(adapter);
        } else {
            // 利用装饰者模式
            adapter = new RecyclerWrapAdapter(mHeaderViews, mFootViews, adapter);
            super.setAdapter(adapter);
        }
        mAdapter = adapter;
    }

    public void notifyDataSetChanged() {
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

}
