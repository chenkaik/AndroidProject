package com.android.lib.util;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * date: 2019/1/30
 * desc: 选择系统相册图片及拍照的工具类
 */
public class ImageUtils {

    public static String date = "";

    private static String photoPath;

    /**
     * 打开系统相册
     *
     * @param activity    act
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
     * @param activity    act
     * @param requestCode 请求码
     */
    @SuppressLint("SimpleDateFormat")
    public static void openCamera(AppCompatActivity activity, int requestCode) {
        date = new SimpleDateFormat("yyyy_MMdd_hhmmss").format(new Date());
        photoPath = createImagePath(activity, date);
        if (TextUtils.isEmpty(photoPath)) {
            Toast.makeText(activity, "图片存储路径创建失败!", Toast.LENGTH_SHORT).show();
            return;
        }
        File file = new File(photoPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (SystemInfo.hasN()) { // Android7.0以上URI
            // 添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            // 通过FileProvider创建一个content类型的Uri activity.getPackageName()+".fileProvider"
            Uri uri = FileProvider.getUriForFile(activity, "com.xxx.fileProvider", file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        }
        try {
            activity.startActivityForResult(intent, requestCode);
        } catch (Exception anf) {
            Toast.makeText(activity, "摄像头未准备好", Toast.LENGTH_SHORT).show();
            //ActivityNotFoundException
        }
    }

    /**
     * 创建图片的存储路径
     *
     * @param activity  act
     * @param imageName 图片名称
     * @return 图片路径
     */
    private static String createImagePath(AppCompatActivity activity, String imageName) {
        File cacheDir = activity.getExternalCacheDir();
        if (cacheDir != null) {
            String dir = cacheDir.getPath();
            File destDir = new File(dir);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }
            File file = null;
            if (!TextUtils.isEmpty(imageName)) {
                file = new File(dir, imageName + ".jpg");
            }
            return file == null ? "" : file.getAbsolutePath();
        } else {
            return "";
        }
    }

}
