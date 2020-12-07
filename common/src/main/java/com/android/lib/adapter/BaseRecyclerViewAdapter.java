package com.android.lib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.lib.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * date: 2019/1/30
 * desc: RecyclerView adapter基类
 * 封装了数据集合以及ItemView的点击事件回调,同时暴露 {@link #onBindData(RecyclerViewHolder, Object, int)}
 * 用于数据与view绑定
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private static final String TAG = "BaseRecyclerViewAdapter";
    private Context mContext;
    private List<T> mData;
    private int mLayoutId;
    private OnItemClickListener mListener;
    private OnItemLongClickListener mLongListener;
    protected OnViewClickListener mOnViewClickListener; // item子view点击事件

    public BaseRecyclerViewAdapter(Context context, List<T> data, int layoutId) {
        init(context, data, layoutId);
    }

    public BaseRecyclerViewAdapter(Context context, List<T> data, int layoutId, OnViewClickListener onViewClickListener) {
        init(context, data, layoutId);
        this.mOnViewClickListener = onViewClickListener;
    }

    /**
     * 初始化
     *
     * @param context  上下文
     * @param data     数据源
     * @param layoutId 布局id
     */
    private void init(Context context, List<T> data, int layoutId) {
        this.mContext = context;
        this.mData = data == null ? new ArrayList<T>() : data;
        this.mLayoutId = layoutId;
    }

    // @Nullable 表示定义的字段可以为空.
    // @NonNull指明一个参数，字段或者方法的返回值不可以为null
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        if (mListener != null) {
            itemView.setOnClickListener(this);
        }
        if (mLongListener != null) {
            itemView.setOnLongClickListener(this);
        }
        Logger.e(TAG, "onCreateViewHolder");
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
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
        void onItemClick(RecyclerView.Adapter<?> adapter, View v, int position);
    }

    /**
     * item长按监听器
     */
    public interface OnItemLongClickListener {
        void onItemLongClick(RecyclerView.Adapter<?> adapter, View v, int position);
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

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        // 当Item进入这个页面的时候调用
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        // 当Item离开这个页面的时候调用
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerViewHolder holder) {
        super.onViewRecycled(holder);
        // 当Item被回收的时候调用
    }

}
