package com.example.android.project.util;

import android.Manifest;

/**
 * @date: 2019/3/4
 * @describe: 根据需要添加对应的权限
 */
public class PermissionGroup {

    // Phone权限
    public static String[] PHONE = new String[]{
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CALL_PHONE
    };

}
