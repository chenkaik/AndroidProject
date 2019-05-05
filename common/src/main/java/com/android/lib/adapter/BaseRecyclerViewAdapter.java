package com.android.lib.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * date: 2019/1/30
 * desc: RecyclerView adapter基类
 * 封装了数据集合以及ItemView的点击事件回调,同时暴露 {@link #onBindData(RecyclerViewHolder, Object, int)}
 * 用于数据与view绑定
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    //    private static final String TAG = "BaseRecyclerViewAdapter";
    private Context context;
    private List<T> data;
    private int layoutId;
    private OnItemClickListener listener;
    private OnItemLongClickListener longListener;

    public BaseRecyclerViewAdapter(Context context, List<T> data, int layoutId) {
        this.context = context;
        this.data = data;
        this.layoutId = layoutId;
    }

    // @Nullable 表示定义的字段可以为空.
    // @NonNull指明一个参数，字段或者方法的返回值不可以为null
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        if (listener != null) {
            itemView.setOnClickListener(this);
        }
        if (longListener != null) {
            itemView.setOnLongClickListener(this);
        }
//        Logger.e(TAG,"onCreateViewHolder");
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
//        Logger.e(TAG,"onBindViewHolder " + position);
        holder.itemView.setTag(position); // 点击事件的位置
        T bean = data.get(position);
        onBindData(holder, bean, position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onItemClick(this, v, (Integer) v.getTag());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (longListener != null) {
            longListener.onItemLongClick(this, v, (Integer) v.getTag());
        }
        return true;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.longListener = onItemLongClickListener;
    }

    /**
     * 数据绑定，由实现类实现
     */
    protected abstract void onBindData(RecyclerViewHolder holder, T bean, int position);

    /**
     * item点击监听器
     */
    public interface OnItemClickListener {
        void onItemClick(RecyclerView.Adapter adapter, View v, int position);
    }

    /**
     * item长按监听器
     */
    public interface OnItemLongClickListener {
        void onItemLongClick(RecyclerView.Adapter adapter, View v, int position);
    }

}
