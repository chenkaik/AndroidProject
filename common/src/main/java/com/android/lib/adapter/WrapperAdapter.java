package com.android.lib.adapter;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author: chen_kai
 * @date：2019/1/30
 * @desc：包装的adapter(RecyclerView添加头部与底部使用)
 */
public interface WrapperAdapter {

    /**
     * 包装adapter
     *
     * @return adapter
     */
    RecyclerView.Adapter getWrappedAdapter();

}
