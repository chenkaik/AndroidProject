package com.android.lib.util;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * date: 2019/1/30
 * desc: Glide加载图片
 */
public class ImageLoader {

    /**
     * 加载资源图片
     *
     * @param resourceId 资源图片id
     * @param imageView  ImageView
     */
    public static void load(@DrawableRes int resourceId, @NonNull ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(resourceId)
                .into(imageView);
    }


    /**
     * 加载配置好加载参数的资源图片
     *
     * @param resourceId 资源图片id
     * @param imageView  ImageView
     * @param options    配置好的加载参数
     */
    public static void load(@DrawableRes int resourceId, @NonNull ImageView imageView, RequestOptions options) {
        Glide.with(imageView.getContext())
                .load(resourceId)
                .apply(options)
                .into(imageView);
    }

    /**
     * 加载本地图片
     *
     * @param path      图片路径
     * @param imageView ImageView
     */
    public static void load(String path, @NonNull ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(path)
                .thumbnail(0.1f)
                .into(imageView);
    }

    /**
     * 加载配置好的图片加载参数
     *
     * @param context   上下文
     * @param url       图片路径
     * @param imageView 控件
     * @param options   配置好的加载参数
     */
    public static void load(Context context, String url, ImageView imageView, RequestOptions options) {
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    /**
     * 加载网络图片
     *
     * @param path             图片路径
     * @param imageView        ImageView
     * @param placeholderResId 占位图
     * @param errorResId       加载失败显示图片
     */
    public static void load(String path, @NonNull ImageView imageView, @DrawableRes int placeholderResId, @DrawableRes int errorResId) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(placeholderResId) // 占位图
                .error(errorResId) // 下载错误
//                .diskCacheStrategy(DiskCacheStrategy.NONE) // 禁止Glide对图片进行硬盘缓存
//                .skipMemoryCache(true) // 禁用内存缓存
                .circleCrop(); // centerCrop、fitCenter、circleCrop
        Glide.with(imageView.getContext())
                .load(path)
                .apply(requestOptions)
                .into(imageView);
    }

    /**
     * 清除View加载的图片
     *
     * @param view 加载图片的View
     */
    public static void clear(Context context, @NonNull View view) {
        Glide.with(context).clear(view);
    }

    /**
     * 清除内存缓存
     *
     * @param context 上下文
     */
    public static void clearMemory(Context context) {
        Glide.get(context).clearMemory();
    }

}
