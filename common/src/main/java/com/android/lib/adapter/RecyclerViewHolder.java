package com.android.lib.adapter;

import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * date: 2019/1/30
 * desc: recyclerView公用的ViewHolder
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private View mConvertView;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        this.mConvertView = itemView;
        mViews = new SparseArray<>();
    }

    /**
     * 通过id获得控件
     *
     * @param id  id
     * @param <K> k
     * @return 控件
     */
    public <K extends View> K findView(int id) {
        return (K) itemView.findViewById(id);
    }

    /**
     * 通过id获得控件
     *
     * @param viewId id
     * @param <T>    控件
     * @return 控件
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 绑定对应值
     *
     * @param viewId 控件id
     * @param text   文本内容
     */
    public void setText(int viewId, String text) {
        TextView textView = getView(viewId);
        if (TextUtils.isEmpty(text)) {
            textView.setText("");
        } else {
            textView.setText(text);
        }
    }

    /**
     * 绑定对应值
     *
     * @param viewId 控件id
     * @param text   文本内容
     * @param value  其它
     */
    public void setText(int viewId, String text, String value) {
        TextView textView = getView(viewId);
        if (TextUtils.isEmpty(value)) {
            textView.setText(text);
        } else {
//            StringBuilder builder = new StringBuilder();
//            builder.append(text);
//            builder.append(value);
//            textView.setText(builder.toString());
            textView.setText(text + value);
        }
    }

}
