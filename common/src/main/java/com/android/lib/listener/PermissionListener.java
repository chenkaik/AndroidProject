package com.android.lib.listener;

import java.util.List;

/**
 * date: 2017/11/17.
 * desc: 用于权限申请回调
 */

public interface PermissionListener {

    void onGranted(); // 同意权限

    void onDenied(List<String> deniedPermissions); // 拒绝权限

}
