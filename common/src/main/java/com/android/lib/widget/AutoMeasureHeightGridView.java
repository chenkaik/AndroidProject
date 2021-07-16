package com.android.lib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @author: chen_kai
 * @date：2019/1/30
 * @desc：根据item多少，自适应高度的列表
 */
public final class AutoMeasureHeightGridView extends GridView {

    public AutoMeasureHeightGridView(Context context) {
        super(context);
    }

    public AutoMeasureHeightGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoMeasureHeightGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

///        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, expandSpec);

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
///        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
//        int widthSize = getMeasuredWidth();
//        heightMeasureSpec =
//                widthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
