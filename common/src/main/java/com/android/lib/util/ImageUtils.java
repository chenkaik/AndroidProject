package com.android.lib.util;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @date: 2019/1/30
 * @author: Kai
 * @describe: 选择系统相册图片及拍照的工具类
 */
public class ImageUtils {

    public static String date = "";

    public static String photoPath;

    /**
     * 打开系统相册
     *
     * @param activity
     * @param requestCode 请求码
     */
    public static void openAlbum(AppCompatActivity activity, int requestCode) {
        Intent intent;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
        } else {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 打开系统相机
     *
     * @param activity
     * @param requestCode 请求码
     */
    public static void openCamera(AppCompatActivity activity, int requestCode) {
        date = new SimpleDateFormat("yyyy_MMdd_hhmmss").format(new Date());
        photoPath = createImagePath(activity, date);
        File file = new File(photoPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Android7.0以上URI
        if (SystemInfo.hasN()) {
            // 添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            // 通过FileProvider创建一个content类型的Uri
            Uri uri = FileProvider.getUriForFile(activity, "com.jacf.spms.fileProvider", file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        }
        try {
            activity.startActivityForResult(intent, requestCode);
        } catch (Exception anf) {
            //Toast.makeText(activity, "摄像头未准备好", Toast.LENGTH_SHORT).show(); ActivityNotFoundException
        }
    }

    /**
     * 创建图片的存储路径
     *
     * @param activity
     * @param imageName
     * @return
     */
    public static String createImagePath(AppCompatActivity activity, String imageName) {
        String dir = activity.getExternalCacheDir().getPath();
        File destDir = new File(dir);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        File file = null;
        if (!TextUtils.isEmpty(imageName)) {
            file = new File(dir, imageName + ".jpg");
        }
        return file.getAbsolutePath();
    }

}
