package com.android.lib.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.android.common.R;
import com.android.lib.util.FragmentManagerUtil;
import com.android.lib.util.ScreenManager;

/**
 * @author: chen_kai
 * @date：2019/1/30
 * @desc：公用的title头部
 */
public final class NavigationBar extends FrameLayout {

    private LayoutInflater mLayoutInflater;
    private LinearLayout mLeftLinearLayout;
    private LinearLayout mCenterLinearLayout;
    private LinearLayout mRightLinearLayout;
    private ImageView mBackButton;
    private TextView mTitleTextView;
    private LinearLayout mRoot;
    private TextView mLeftTextView;

    public NavigationBar(Context context) {
        super(context);
        initComponent();
    }

    public NavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initComponent();
    }

    private void initComponent() {
        mLayoutInflater = LayoutInflater.from(getContext());
        mRoot = (LinearLayout) mLayoutInflater.inflate(R.layout.navigation_bar, this, false);
        mLeftLinearLayout = (LinearLayout) mRoot.findViewById(R.id.leftLinearLayout);
        mCenterLinearLayout = (LinearLayout) mRoot.findViewById(R.id.centerLinearLayout);
        mRightLinearLayout = (LinearLayout) mRoot.findViewById(R.id.rightLinearLayout);
        mTitleTextView = (TextView) mRoot.findViewById(R.id.titleTextView);
        mLeftTextView = (TextView) mRoot.findViewById(R.id.navigation_title_bar_back_view);
        mBackButton = (ImageView) mRoot.findViewById(R.id.navigation_title_bar_back);
///        mLeftLinearLayout.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                backButtonOnClick(v);
//            }
//        });
        mLeftLinearLayout.setOnClickListener(this::backButtonOnClick);
        addView(mRoot);
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
        mTitleTextView.setText(title);
    }

    public void addRightView(View view) {
        mRightLinearLayout.addView(view);
    }

    public void clearRightViews() {
        mRightLinearLayout.removeAllViews();
    }

    public void addLeftView(View view) {
        mLeftLinearLayout.addView(view);
    }

    public void clearLeftViews() {
        mLeftLinearLayout.removeAllViews();
    }

    public void showBackButton() {
        mBackButton.setVisibility(View.VISIBLE);
    }

    public void hideLeftLayout() {
        mLeftLinearLayout.setVisibility(View.INVISIBLE);
    }

    public void hideBackButton() {
        mBackButton.setVisibility(GONE);
    }

    public void hideBackTextView() {
        mLeftTextView.setVisibility(GONE);
    }

    public View getBackBackButton() {
        return mBackButton;
    }

    public void setNavigationBarBackgroundColor(int color) {
        mRoot.setBackgroundColor(color);
    }

    public void setBackButtonOnClickListener(View.OnClickListener onClickListener) {
        mBackButton.setOnClickListener(onClickListener);
    }

}
