package com.android.lib.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * @date: 2019/1/30
 * @describe:  RecyclerView adapter基类
 * 封装了数据集合以及ItemView的点击事件回调,同时暴露 {@link #onBindData(RecyclerViewHolder, Object, int)}
 * 用于数据与view绑定
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> implements View.OnClickListener, View.OnLongClickListener {

//    private static final String TAG = "BaseRecyclerViewAdapter";
    private Context mContext;
    private List<T> mData;
    private int mLayoutId;
    private OnItemClickListener mListener;
    private OnItemLongClickListener mLongListener;

    public BaseRecyclerViewAdapter(Context context, List<T> data, int layoutId) {
        this.mContext = context;
        this.mData = data;
        this.mLayoutId = layoutId;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        if (mListener != null) {
            itemView.setOnClickListener(this);
        }
        if (mLongListener != null) {
            itemView.setOnLongClickListener(this);
        }
//        Logger.e(TAG,"onCreateViewHolder");
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
//        Logger.e(TAG,"onBindViewHolder " + position);
        holder.itemView.setTag(position); // 点击事件的位置
        T bean = mData.get(position);
        onBindData(holder, bean, position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onItemClick(this, v, (Integer) v.getTag());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mLongListener != null) {
            mLongListener.onItemLongClick(this, v, (Integer) v.getTag());
        }
        return true;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mLongListener = onItemLongClickListener;
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
