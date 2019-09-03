package com.android.lib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * date: 2019/1/30
 * desc: 封装的基类adapter
 */
public class RecyclerViewBaseAdapter<T, VH extends RecyclerViewHolder> extends RecyclerView.Adapter<VH> {
    public Context mContext; // 上下文
    public List<T> mData; // 数据源
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener; // item点击事件
    private OnItemLongClickListener mOnItemLongClickListener; // item长按事件
    private OnViewClickListener mOnViewClickListener; // item子view点击事件

    /**
     * 默认构造方法
     *
     * @param context 上下文
     * @param data    数据源
     */
    public RecyclerViewBaseAdapter(Context context, List<T> data) {
        init(context, data);
    }

    /**
     * 如果item的子View有点击事件，使用该构造方法
     *
     * @param context             上下文
     * @param data                数据源
     * @param onViewClickListener 子view的监听
     */
    public RecyclerViewBaseAdapter(Context context, List<T> data, OnViewClickListener onViewClickListener) {
        init(context, data);
        this.mOnViewClickListener = onViewClickListener;
    }

    /**
     * 初始化
     *
     * @param context 上下文
     * @param data    数据源
     */
    void init(Context context, List<T> data) {
        this.mContext = context;
        this.mData = data == null ? new ArrayList<T>() : data;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
//        if (onItemClickListener != null) {
//            holder.itemView.setOnClickListener(new View.OnClickListener() { // item点击事件
//                @Override
//                public void onClick(View v) {
//                    onItemClickListener.onItemClick(position);
//                }
//            });
//        }
//
//        if (onItemLongClickListener != null) {
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() { // item长按事件
//                @Override
//                public boolean onLongClick(View v) {
//                    onItemLongClickListener.onItemLongClick(position);
//                    return true;
//                }
//            });
//        }


//        if (onItemClickListener != null) {
//            holder.itemView.setOnClickListener(this);
//        }
//        if (onItemLongClickListener != null) {
//            holder.itemView.setOnLongClickListener(this);
//        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * item点击事件
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    /**
     * 设置item点击事件
     *
     * @param onItemClickListener item点击事件监听
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /**
     * item长按事件
     */
    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    /**
     * 设置item长按事件
     *
     * @param onItemLongClickListener tem长按事件监听
     */
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    /**
     * item中子view的点击事件（回调）
     */
    public interface OnViewClickListener {
        /**
         * @param position item position
         * @param type     点击的view的类型，调用时根据不同的view传入不同的值加以区分
         */
        void onViewClick(int position, int type);
    }

}
