package com.example.android.project.adapter;

import android.content.Context;
import android.widget.TextView;

import com.android.lib.Logger;
import com.android.lib.adapter.BaseRecyclerViewAdapter;
import com.android.lib.adapter.RecyclerViewHolder;
import com.example.android.project.R;
import com.example.android.project.entity.Test;

import java.util.List;

/**
 * @date: 2019/2/24
 * @describe:
 */
public class TestAdapter extends BaseRecyclerViewAdapter<Test> {
    private static final String TAG = "TestAdapter";

    public TestAdapter(Context context, List<Test> data) {
        super(context, data, R.layout.adapter_test);
    }

    @Override
    protected void onBindData(RecyclerViewHolder holder, Test bean, int position) {
        Logger.e(TAG, "onBindData " + position);
        TextView textView = holder.getView(R.id.test_textView);
        textView.setText(bean.getName());
    }
}
