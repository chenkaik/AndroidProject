package com.android.lib.listener;

import java.util.List;

/**
 * @author: chen_kai
 * @date：2017/11/17
 * @desc：用于权限申请回调
 */
public interface PermissionListener {

    /**
     * 同意权限
     */
    void onGranted();

    /**
     * 拒绝权限
     *
     * @param deniedPermissions 拒绝权限的集合
     */
    void onDenied(List<String> deniedPermissions);

}
