package com.android.lib.banner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * date: 2019/1/30
 * desc: banner使用的baseAdapter
 */
public abstract class BaseBannerAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<Integer> mUrlList;
    protected Context mContext;

    public BaseBannerAdapter(Context context, List<Integer> urlList) {
        this.mUrlList = urlList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public final VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return createCustomViewHolder(parent, viewType);
    }

    /**
     * 创建自定义的ViewHolder
     *
     * @param parent   父类容器
     * @param viewType view类型{@link #getItemViewType(int)}
     * @return ImgHolder
     */
    protected abstract VH createCustomViewHolder(ViewGroup parent, int viewType);


    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindCustomViewHolder((VH) holder, position);
    }

    /**
     * 绑定自定义的ViewHolder
     *
     * @param holder   ImgHolder
     * @param position 位置
     */
    public abstract void bindCustomViewHolder(VH holder, int position);

    @Override
    public int getItemCount() {
        // 如果只有一张图片就不进行滑动了
        return mUrlList.size() < 2 ? 1 : Integer.MAX_VALUE;
    }

}
