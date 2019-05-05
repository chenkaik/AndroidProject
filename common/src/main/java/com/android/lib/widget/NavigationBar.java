package com.android.lib.widget;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.common.R;
import com.android.lib.util.FragmentManagerUtil;
import com.android.lib.util.ScreenManager;

/**
 * date: 2019/1/30
 * desc: 公用的title头部
 */
public class NavigationBar extends FrameLayout {

    private LayoutInflater layoutInflater;
    private LinearLayout leftLinearLayout;
    private LinearLayout centerLinearLayout;
    private LinearLayout rightLinearLayout;
    private ImageView backButton;
    private TextView titleTextView;
    private LinearLayout root;
    private TextView leftTextView;

    public NavigationBar(Context context) {
        super(context);
        initComponent();
    }

    public NavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initComponent();
    }

    private void initComponent() {
        layoutInflater = LayoutInflater.from(getContext());
        root = (LinearLayout) layoutInflater.inflate(R.layout.navigation_bar, this, false);
        leftLinearLayout = (LinearLayout) root.findViewById(R.id.leftLinearLayout);
        centerLinearLayout = (LinearLayout) root.findViewById(R.id.centerLinearLayout);
        rightLinearLayout = (LinearLayout) root.findViewById(R.id.rightLinearLayout);
        titleTextView = (TextView) root.findViewById(R.id.titleTextView);
        leftTextView = (TextView) root.findViewById(R.id.navigation_title_bar_back_view);
        backButton = (ImageView) root.findViewById(R.id.navigation_title_bar_back);
        leftLinearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                backButtonOnClick(v);
            }
        });
        addView(root);
    }

    private void backButtonOnClick(View v) {
        if (getContext() instanceof FragmentActivity) {
            FragmentActivity activity = ((FragmentActivity) getContext());
            if (ScreenManager.getScreenManager().goBlackPage() && activity.getSupportFragmentManager().getBackStackEntryCount() == 0) {
                activity.finish();
            } else {
                activity.getSupportFragmentManager().popBackStack();
            }
        } else {
            ((Activity) getContext()).finish();
        }
        FragmentManagerUtil.hideSoftInput((Activity) getContext());
    }

    public void setTitle(String title) {
        titleTextView.setText(title);
    }

    public void addRightView(View view) {
        rightLinearLayout.addView(view);
    }

    public void clearRightViews() {
        rightLinearLayout.removeAllViews();
    }

    public void addLeftView(View view) {
        leftLinearLayout.addView(view);
    }

    public void clearLeftViews() {
        leftLinearLayout.removeAllViews();
    }

    public void showBackButton() {
        backButton.setVisibility(View.VISIBLE);
    }

    public void hideLeftLayout() {
        leftLinearLayout.setVisibility(View.INVISIBLE);
    }

    public void hideBackButton() {
        backButton.setVisibility(GONE);
    }

    public void hideBackTextView() {
        leftTextView.setVisibility(GONE);
    }

    public View getBackBackButton() {
        return backButton;
    }

    public void setNavigationBarBackgroundColor(int color) {
        root.setBackgroundColor(color);
    }

    public void setBackButtonOnClickListener(View.OnClickListener onClickListener) {
        backButton.setOnClickListener(onClickListener);
    }

}
