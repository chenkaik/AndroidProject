package com.android.lib.banner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.lib.util.ImageLoader;

import java.util.List;

/**
 * date: 2019/1/30
 * desc: banner使用的adapter
 */
public class BannerRecyclerAdapter extends BaseBannerAdapter<BannerRecyclerAdapter.NormalHolder> {
    private static final String TAG = "BannerRecyclerAdapter";

    private RecyclerViewBannerBaseView.OnBannerItemClickListener onBannerItemClickListener;

    public BannerRecyclerAdapter(Context context, List urlList, RecyclerViewBannerBaseView.OnBannerItemClickListener onBannerItemClickListener) {
        super(context, urlList);
        this.onBannerItemClickListener = onBannerItemClickListener;
    }

    @Override
    protected BannerRecyclerAdapter.NormalHolder createCustomViewHolder(ViewGroup parent, int viewType) {
//        Logger.e(TAG, "createCustomViewHolder: " + viewType);
        return new NormalHolder(new ImageView(mContext));
    }

    @Override
    public void bindCustomViewHolder(NormalHolder holder, final int position) {
//        Logger.e(TAG, "bindCustomViewHolder: " + position);
        if (mUrlList == null || mUrlList.isEmpty()) {
            return;
        }
        Integer url = mUrlList.get(position % mUrlList.size());
//        ImageView imageView = (ImageView) holder.itemView;
        ImageView imageView = holder.bannerItem;
        ImageLoader.load(url, imageView);
        // 去掉点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBannerItemClickListener != null) {
                    onBannerItemClickListener.onItemClick(position % mUrlList.size());
                }
            }
        });
    }

    static class NormalHolder extends RecyclerView.ViewHolder {

        ImageView bannerItem;

        NormalHolder(View itemView) {
            super(itemView);
            bannerItem = (ImageView) itemView;
            RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            bannerItem.setLayoutParams(params);
            // 把图片按照指定的大小在ImageView中显示，拉伸显示图片，不保持原比例，填满ImageView.
            bannerItem.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }

}
