package com.android.lib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author: chen_kai
 * @date：2019/1/30
 * @desc：底部导航
 */
public final class TabGroupLayout extends LinearLayout {

    public static final String TAG = TabGroupLayout.class.getSimpleName();

    public TabGroupLayout(Context context) {
        super(context);
    }

    public TabGroupLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void select(int index) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View v = getChildAt(i);
            v.setSelected(i == index);
        }
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View v = getChildAt(i);
            v.setOnClickListener(l);
        }
    }

}
