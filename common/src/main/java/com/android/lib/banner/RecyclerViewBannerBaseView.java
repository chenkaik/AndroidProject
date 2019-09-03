package com.android.lib.banner;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.android.common.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * date: 2019/1/30
 * desc: RecyclerView实现banner
 */
public abstract class RecyclerViewBannerBaseView<L extends RecyclerView.LayoutManager, A extends BaseBannerAdapter> extends FrameLayout {
    protected int autoPlayDuration; // 刷新间隔时间

    protected boolean showIndicator; // 是否显示指示器
    protected RecyclerView indicatorContainer;
    protected Drawable mSelectedDrawable;
    protected Drawable mUnselectedDrawable;
    protected IndicatorAdapter indicatorAdapter;
    protected int indicatorMargin; // 指示器间距

    protected RecyclerView mRecyclerView;
    protected A adapter;
    protected L mLayoutManager;

    protected int WHAT_AUTO_PLAY = 1000;

    protected boolean hasInit;
    protected int bannerSize = 1;
    protected int currentIndex;
    protected boolean isPlaying;

    protected boolean isAutoPlaying;

    protected OnBannerItemClickListener onBannerItemClickListener;


    protected Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == WHAT_AUTO_PLAY) {
                mRecyclerView.smoothScrollToPosition(++currentIndex);
                refreshIndicator();
                mHandler.sendEmptyMessageDelayed(WHAT_AUTO_PLAY, autoPlayDuration);
            }
            return false;
        }
    });

    public RecyclerViewBannerBaseView(Context context) {
        this(context, null);
    }

    public RecyclerViewBannerBaseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerViewBannerBaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    protected void initView(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RecyclerViewBannerBaseView);
        showIndicator = a.getBoolean(R.styleable.RecyclerViewBannerBaseView_showIndicator, true); // 是否显示指示器
        autoPlayDuration = a.getInt(R.styleable.RecyclerViewBannerBaseView_interval, 4000); // 自动播放的时间间隔
        isAutoPlaying = a.getBoolean(R.styleable.RecyclerViewBannerBaseView_autoPlaying, true); // 是否自动播放
        mSelectedDrawable = a.getDrawable(R.styleable.RecyclerViewBannerBaseView_indicatorSelectedSrc); // 滑动选中的样式
        mUnselectedDrawable = a.getDrawable(R.styleable.RecyclerViewBannerBaseView_indicatorUnselectedSrc); // 滑动未选中的样式
        if (mSelectedDrawable == null) {
            // 绘制默认选中状态图形
            GradientDrawable selectedGradientDrawable = new GradientDrawable();
            selectedGradientDrawable.setShape(GradientDrawable.OVAL);
            selectedGradientDrawable.setColor(getColor(R.color.colorPrimaryDark));
            selectedGradientDrawable.setSize(dp2px(6), dp2px(6));
            selectedGradientDrawable.setCornerRadius(dp2px(6) / 2);
            mSelectedDrawable = new LayerDrawable(new Drawable[]{selectedGradientDrawable});
        }
        if (mUnselectedDrawable == null) {
            // 绘制默认未选中状态图形
            GradientDrawable unSelectedGradientDrawable = new GradientDrawable();
            unSelectedGradientDrawable.setShape(GradientDrawable.OVAL);
            unSelectedGradientDrawable.setColor(getColor(R.color.colorAccent));
            unSelectedGradientDrawable.setSize(dp2px(6), dp2px(6));
            unSelectedGradientDrawable.setCornerRadius(dp2px(6) / 2);
            mUnselectedDrawable = new LayerDrawable(new Drawable[]{unSelectedGradientDrawable});
        }
        // 指示器间距
        indicatorMargin = a.getDimensionPixelSize(R.styleable.RecyclerViewBannerBaseView_indicatorSpaces, dp2px(4));
        // 指示器左间距
        int marginLeft = a.getDimensionPixelSize(R.styleable.RecyclerViewBannerBaseView_indicatorMarginLeft, dp2px(0));
        // 指示器右间距
        int marginRight = a.getDimensionPixelSize(R.styleable.RecyclerViewBannerBaseView_indicatorMarginRight, dp2px(0));
        // 指示器离下面间距
        int marginBottom = a.getDimensionPixelSize(R.styleable.RecyclerViewBannerBaseView_indicatorMarginBottom, dp2px(10));
        // 指示器的位置
        int g = a.getInt(R.styleable.RecyclerViewBannerBaseView_indicatorGravity, 3);
        int gravity;
        if (g == 0) {
            gravity = GravityCompat.START;
        } else if (g == 2) {
            gravity = GravityCompat.END;
        } else {
            gravity = Gravity.CENTER;
        }
        // 指示器排序
        int o = a.getInt(R.styleable.RecyclerViewBannerBaseView_orientation, 0);
        int orientation = 0;
        if (o == 0) {
            orientation = LinearLayoutManager.HORIZONTAL;
        } else if (o == 1) {
            orientation = LinearLayoutManager.VERTICAL;
        }
        a.recycle();
        // recyclerView部分
        mRecyclerView = new RecyclerView(context);
        new PagerSnapHelper().attachToRecyclerView(mRecyclerView);
        mLayoutManager = getLayoutManager(context, orientation);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            /**
             * dx、dy这两个参数，当向上滑动的时候dy是大于0的，
             * 向左滑动的时候dx是大于0的，反方向滑动则小于0，
             */
            @Override
            public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
                onBannerScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int newState) {
                onBannerScrollStateChanged(recyclerView, newState);
            }
        });
        LayoutParams vpLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(mRecyclerView, vpLayoutParams);

        // 指示器部分
        indicatorContainer = new RecyclerView(context);
        LinearLayoutManager indicatorLayoutManager = new LinearLayoutManager(context, orientation, false);
        indicatorContainer.setLayoutManager(indicatorLayoutManager);
        indicatorAdapter = new IndicatorAdapter();
        indicatorContainer.setAdapter(indicatorAdapter);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM | gravity;
        params.setMargins(marginLeft, 0, marginRight, marginBottom);
        addView(indicatorContainer, params);
        if (!showIndicator) {
            indicatorContainer.setVisibility(GONE);
        }
    }

    /**
     * 设置轮播间隔时间
     *
     * @param millisecond 时间毫秒
     */
    public void setIndicatorInterval(int millisecond) {
        this.autoPlayDuration = millisecond;
    }

    /**
     * 设置是否自动播放（上锁）
     *
     * @param playing 开始播放
     */
    protected synchronized void setPlaying(boolean playing) {
        if (isAutoPlaying && hasInit) {
            if (!isPlaying && playing && adapter != null && adapter.getItemCount() > 2) {
                mHandler.sendEmptyMessageDelayed(WHAT_AUTO_PLAY, autoPlayDuration);
                isPlaying = true;
            } else if (isPlaying && !playing) {
                mHandler.removeMessages(WHAT_AUTO_PLAY);
                isPlaying = false;
            }
        }
    }

    /**
     * 设置是否禁止滚动播放
     */
    public void setAutoPlaying(boolean isAutoPlaying) {
        this.isAutoPlaying = isAutoPlaying;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setShowIndicator(boolean showIndicator) {
        this.showIndicator = showIndicator;
        indicatorContainer.setVisibility(showIndicator ? VISIBLE : GONE);
    }

    public void setOnBannerItemClickListener(OnBannerItemClickListener onBannerItemClickListener) {
        this.onBannerItemClickListener = onBannerItemClickListener;
    }

    /**
     * 设置轮播数据集
     */
    public void initBannerImageView(@NonNull List<Integer> newList, OnBannerItemClickListener onBannerItemClickListener) {
        // 解决recyclerView嵌套问题
        hasInit = false;
        setVisibility(VISIBLE);
        setPlaying(false);
        adapter = getAdapter(getContext(), newList, onBannerItemClickListener);
        mRecyclerView.setAdapter(adapter);
        bannerSize = newList.size();
        if (bannerSize > 1) {
            indicatorContainer.setVisibility(VISIBLE);
            currentIndex = bannerSize * 10000;
            mRecyclerView.scrollToPosition(currentIndex);
            indicatorAdapter.notifyDataSetChanged();
            setPlaying(true);
        } else {
            indicatorContainer.setVisibility(GONE);
            currentIndex = 0;
        }
        hasInit = true;
    }

    /**
     * 设置轮播数据集
     */
    public void initBannerImageView(@NonNull List<Integer> newList) {
        initBannerImageView(newList, null);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: // 按住没松开
                setPlaying(false);
                break;
            case MotionEvent.ACTION_UP: // 离开屏幕
            case MotionEvent.ACTION_CANCEL:
                setPlaying(true);
                break;
        }
        // 解决recyclerView嵌套问题
        try {
            return super.dispatchTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // 解决recyclerView嵌套问题
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // 解决recyclerView嵌套问题
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setPlaying(true);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setPlaying(false);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility == VISIBLE) {
            setPlaying(true);
        } else {
            setPlaying(false);
        }
    }

    /**
     * 标示点适配器
     */
    protected class IndicatorAdapter extends RecyclerView.Adapter {

        private static final String TAG = "IndicatorAdapter";

        int currentPosition = 0;

        public void setPosition(int currentPosition) {
            this.currentPosition = currentPosition;
        }

        @NotNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
            //Logger.e(TAG, "onCreateViewHolder: " + viewType );
            ImageView bannerPoint = new ImageView(getContext());
            RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(indicatorMargin, indicatorMargin, indicatorMargin, indicatorMargin);
            bannerPoint.setLayoutParams(lp);
            return new RecyclerView.ViewHolder(bannerPoint) {
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            //Logger.e(TAG, "onBindViewHolder: " + position);
            ImageView bannerPoint = (ImageView) holder.itemView;
            bannerPoint.setImageDrawable(currentPosition == position ? mSelectedDrawable : mUnselectedDrawable);
        }

        @Override
        public int getItemCount() {
            return bannerSize;
        }
    }

    /**
     * 改变导航的指示点
     */
    protected synchronized void refreshIndicator() {
        if (showIndicator && bannerSize > 1) {
            indicatorAdapter.setPosition(currentIndex % bannerSize);
            indicatorAdapter.notifyDataSetChanged();
        }
    }

    public interface OnBannerItemClickListener {
        void onItemClick(int position);
    }

    /**
     * dp转px
     *
     * @param dp
     * @return
     */
    protected int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }

    /**
     * 获取颜色
     */
    protected int getColor(@ColorRes int color) {
        return ContextCompat.getColor(getContext(), color);
    }

    protected boolean compareListDifferent(List<String> newTabList, List<String> oldTabList) {
        if (oldTabList == null || oldTabList.isEmpty()) {
            return true;
        }
        if (newTabList.size() != oldTabList.size()) {
            return true;
        }
        for (int i = 0; i < newTabList.size(); i++) {
            if (TextUtils.isEmpty(newTabList.get(i))) {
                return true;
            }
            if (!newTabList.get(i).equals(oldTabList.get(i))) {
                return true;
            }
        }
        return false;
    }

    protected abstract void onBannerScrolled(RecyclerView recyclerView, int dx, int dy);

    protected abstract void onBannerScrollStateChanged(RecyclerView recyclerView, int newState);

    protected abstract L getLayoutManager(Context context, int orientation);

    protected abstract A getAdapter(Context context, List<Integer> list, OnBannerItemClickListener onBannerItemClickListener);

}
