package com.example.android.project.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.lib.Logger;
import com.android.lib.adapter.BaseRecyclerViewAdapter;
import com.android.lib.adapter.RecyclerViewHolder;
import com.example.android.project.R;
import com.example.android.project.entity.Test;

import java.util.List;

/**
 * date: 2019/2/24
 * desc:
 */
public class FootAdapter extends BaseRecyclerViewAdapter<Test> {
    private static final String TAG = "FootAdapter";

    public FootAdapter(Context context, List<Test> data) {
        super(context, data, R.layout.adapter_test);
    }

    public FootAdapter(Context context, List<Test> data, OnViewClickListener onViewClickListener) {
        super(context, data, R.layout.adapter_test, onViewClickListener);
    }

    @Override
    protected void onBindData(RecyclerViewHolder holder, Test bean, int position) {
        Logger.e(TAG, "onBindData " + position);
        TextView textView = holder.getView(R.id.test_textView);
        textView.setText(bean.getName());
        textView.setOnClickListener(new ViewListener(mOnViewClickListener, position, 1));
    }


    /**
     * view的点击事件
     */
    class ViewListener implements View.OnClickListener {

        OnViewClickListener onViewClickListener;
        int position;
        int type;

        public ViewListener(OnViewClickListener onViewClickListener, int position, int type) {
            this.onViewClickListener = onViewClickListener;
            this.position = position;
            this.type = type;
        }

        @Override
        public void onClick(View v) {
            if (onViewClickListener != null) {
                onViewClickListener.onViewClick(position, type);
            }
        }

    }

}
