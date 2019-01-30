package com.android.lib.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * @date: 2019/1/30
 * @author: Kai
 * @describe: 包装的adapter(RecyclerView添加头部与底部使用)
 */
public interface WrapperAdapter {

    RecyclerView.Adapter getWrappedAdapter();

}
