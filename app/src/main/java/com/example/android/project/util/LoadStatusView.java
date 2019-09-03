package com.example.android.project.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.lib.Logger;
import com.android.lib.util.NetworkUtil;
import com.example.android.project.R;

/**
 * date: 2019/6/21
 * desc: 布局的操作帮助类
 */
public final class LoadStatusView extends FrameLayout implements View.OnClickListener {

    private static final String TAG = "LoadStatusView";

//    public static int STATUS_LOADING = 1; // 加载中
//    public static int STATUS_NO_NET = 2; // 无网络
//    public static int STATUS_FAIL_REFRESH = 3; // 加载失败点击重新加载
//    public static int STATUS_HIDE = 4; // 隐藏

    private ImageView ivLoadStatus;
    private TextView tvLoadStatus;
    private AnimationDrawable loadAnimationDrawable;
    private OnRefreshListener onRefreshListener = null;

    public LoadStatusView(@NonNull Context context) {
        this(context, null);
    }

    public LoadStatusView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadStatusView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

//    public LoadStatusView(@androidx.annotation.NonNull @NonNull Context context, @androidx.annotation.Nullable @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    private void init() {
        View.inflate(getContext(), R.layout.load_status, this);
        setClickable(true); // 这个是为了不让点击穿透到下层界面去，以免下层的控件看不见却莫名其妙的响应了
        ivLoadStatus = findViewById(R.id.iv_load_anim);
        tvLoadStatus = findViewById(R.id.tv_load_text);
        loadAnimationDrawable = (AnimationDrawable) ivLoadStatus.getBackground();
        tvLoadStatus.setOnClickListener(this);
    }

    /**
     * 加载中
     */
    public void setLoading() {
        setVisibility(View.VISIBLE);
        ivLoadStatus.setVisibility(VISIBLE);
        tvLoadStatus.setVisibility(VISIBLE);
        loadAnimationDrawable.start();
        tvLoadStatus.setText(getResources().getString(R.string.load_status_loading));
        tvLoadStatus.setClickable(false);
        tvLoadStatus.setEnabled(false);
    }

    /**
     * 无网络
     */
    public void setNoNet() {
        setVisibility(View.VISIBLE);
        if (loadAnimationDrawable.isRunning()) {
            Logger.e(TAG, "setNoNet: 进来stop了");
            loadAnimationDrawable.stop();
        }
        ivLoadStatus.setVisibility(GONE);
        tvLoadStatus.setVisibility(VISIBLE);
        tvLoadStatus.setText(getResources().getString(R.string.load_status_no_net));
        tvLoadStatus.setClickable(true);
        tvLoadStatus.setEnabled(true);
    }

    /**
     * 加载失败重新加载
     */
    public void setFailRefresh() {
        setVisibility(View.VISIBLE);
        if (loadAnimationDrawable.isRunning()) {
            Logger.e(TAG, "setFailRefresh: 进来stop了");
            loadAnimationDrawable.stop();
        }
        ivLoadStatus.setVisibility(GONE);
        tvLoadStatus.setVisibility(VISIBLE);
        tvLoadStatus.setText(getResources().getString(R.string.load_status_fail_refresh));
        tvLoadStatus.setClickable(true);
        tvLoadStatus.setEnabled(true);
    }

    /**
     * 隐藏
     */
    public void setHide() {
        if (loadAnimationDrawable.isRunning()) {
            Logger.e(TAG, "setHide: 进来stop了");
            loadAnimationDrawable.stop();
        }
        setVisibility(View.GONE);
        tvLoadStatus.setClickable(false);
        tvLoadStatus.setEnabled(false);
    }


    @Override
    public void onClick(View v) {
        if (v == tvLoadStatus) {
            if (NetworkUtil.isNetworkAvailable(getContext())) {
                if (onRefreshListener != null) {
                    onRefreshListener.onRefreshListener();
                }
            } else {
                openSettings();
            }
        }
    }


    /**
     * 设置点击刷新监听
     *
     * @param listener listener
     */
    public void setOnRefreshListener(OnRefreshListener listener) {
        onRefreshListener = listener;
    }

    /**
     * 打开设置
     */
    private void openSettings() {
        Intent intent = new Intent();
        intent.setAction(android.provider.Settings.ACTION_SETTINGS);
        getContext().startActivity(intent);
    }

    public interface OnRefreshListener {
        void onRefreshListener();
    }

}
