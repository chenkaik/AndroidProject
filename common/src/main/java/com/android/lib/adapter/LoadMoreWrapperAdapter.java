package com.android.lib.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.common.R;


/**
 * date: 2019/1/30
 * desc: 封装上拉加载更多adapter
 */
public class LoadMoreWrapperAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "LoadMoreWrapperAdapter";

    private RecyclerView.Adapter adapter;
    // HeadView
    private View mHeaderView;
    // 头布局
    private final int TYPE_HEADER = 0;
    // 普通布局
    private final int TYPE_ITEM = 1;
    // 脚布局
    private final int TYPE_FOOTER = 2;
    // 正在加载
    private final int LOADING = 1;
    // 加载完成
    private final int LOADING_COMPLETE = 2;
    // 加载到底
    private final int LOADING_END = 3;
    // 当前加载状态，默认为加载完成
    private int loadState = 2;

    public LoadMoreWrapperAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    public void addHeaderView(View headerView) {
        this.mHeaderView = headerView;
        notifyItemInserted(0); // 通知增加单条数据
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView != null && position == 0) { // 第一个item设置为HeadView
            return TYPE_HEADER;
        } else if (position + 1 == getItemCount()) { // 最后一个item设置为FooterView
            return TYPE_FOOTER;
        } else { // 普通View
            return TYPE_ITEM;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 通过判断显示类型，来创建不同的View
        if (viewType == TYPE_HEADER && mHeaderView != null) {
            return new HeadViewHolder(mHeaderView);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_refresh_footer, parent, false);
            return new FootViewHolder(view);
        } else {
            return adapter.onCreateViewHolder(parent, viewType);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            return;
        } else if (holder instanceof FootViewHolder) {
            FootViewHolder footViewHolder = (FootViewHolder) holder;
            switch (loadState) {
                case LOADING: // 正在加载
                    footViewHolder.pbLoading.setVisibility(View.VISIBLE);
                    footViewHolder.tvLoading.setVisibility(View.VISIBLE);
                    footViewHolder.llEnd.setVisibility(View.GONE);
                    break;
                case LOADING_COMPLETE: // 加载完成
                    footViewHolder.pbLoading.setVisibility(View.INVISIBLE);
                    footViewHolder.tvLoading.setVisibility(View.INVISIBLE);
                    footViewHolder.llEnd.setVisibility(View.GONE);
                    break;
                case LOADING_END: // 加载到底
                    footViewHolder.pbLoading.setVisibility(View.GONE);
                    footViewHolder.tvLoading.setVisibility(View.GONE);
                    footViewHolder.llEnd.setVisibility(View.VISIBLE);
//                    footViewHolder.llEnd.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        } else {
            if (mHeaderView != null && position > 0) {
                position--;
            }
            adapter.onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        //return adapter.getItemCount() + 1;
        return mHeaderView != null ? adapter.getItemCount() + 2 : adapter.getItemCount() + 1;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) { // 首先判断当前是否为网格布局
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            // GridLayoutManager设置一个SpanSizeLookup，这是一个抽象类，里面有一个抽象方法getSpanSize，这个方法的返回值决定了每个Item占据的单元格数。
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    //return getItemViewType(position) == TYPE_FOOTER ? gridManager.getSpanCount() : 1;
                    if (getItemViewType(position) == TYPE_HEADER) {
                        // 如果当前是HeaderView则占所有列，否则只占自己列
                        return gridManager.getSpanCount();
                    } else if (getItemViewType(position) == TYPE_FOOTER) {
                        // 如果当前是footer的位置，那么该item占据2个单元格，正常情况下占据1个单元格
                        return gridManager.getSpanCount();
                    } else {
                        // 正常
                        return 1;
                    }
                }
            });
        }
    }

    private static class HeadViewHolder extends RecyclerView.ViewHolder {

        //private SparseArray<View> views;

        public HeadViewHolder(View itemView) {
            super(itemView);
            //views = new SparseArray<View>();
        }

//        private <T extends View> T getView(int resId) {
//            View view = views.get(resId);
//            if(view == null){
//                view = itemView.findViewById(resId);
//            }
//            views.put(resId,view);
//            return (T)view;
//        }

    }

    private static class FootViewHolder extends RecyclerView.ViewHolder {

        ProgressBar pbLoading;
        TextView tvLoading;
        LinearLayout llEnd;

        FootViewHolder(View itemView) {
            super(itemView);
            pbLoading = (ProgressBar) itemView.findViewById(R.id.pb_loading);
            tvLoading = (TextView) itemView.findViewById(R.id.tv_loading);
            llEnd = (LinearLayout) itemView.findViewById(R.id.ll_end);
        }

    }

    /**
     * 设置上拉加载状态
     *
     * @param loadState 1.正在加载 2.加载完成 3.加载到底
     */
    public void setLoadState(int loadState) {
        this.loadState = loadState;
        notifyDataSetChanged();
    }

}
