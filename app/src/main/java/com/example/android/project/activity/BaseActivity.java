package com.example.android.project.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.android.lib.listener.PermissionListener;
import com.android.lib.util.CommonHelp;
import com.android.lib.util.NetworkUtil;
import com.android.lib.util.ScreenManager;
import com.example.android.project.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date: 2019/2/11
 * desc: 基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    private CommonHelp commonHelp;
    private Toast toast;
    private Unbinder mButterKnife; // View注解
    private PermissionListener permissionListener;
    private long exitTime = 0; // 记录退出按下时间

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            requestWindowFeature(Window.FEATURE_NO_TITLE);
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            WindowManager.LayoutParams lp = this.getWindow().getAttributes();
//            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
//            this.getWindow().setAttributes(lp);
//        }
//        DisplayCutoutDemo displayCutoutDemo = new DisplayCutoutDemo(this);
//        displayCutoutDemo.openFullScreenModel();
        setContentView(getLayoutId());
        init();
        initView();
        initData();
    }

    /**
     * 初始化
     */
    private void init() {
        mButterKnife = ButterKnife.bind(this);
        commonHelp = new CommonHelp(this);
        toast = Toast.makeText(this, null, Toast.LENGTH_SHORT);
    }

    // 引入布局
    protected abstract int getLayoutId();

    // 初始化控件
    protected abstract void initView();

    // 初始化数据
    protected abstract void initData();

    /**
     * 显示toast
     *
     * @param message 信息
     */
    public void showToastMessage(String message) {
        if (toast != null) {
            toast.setText(message);
            toast.show();
        }
    }

    /**
     * 显示dialog
     *
     * @param msg 信息
     */
    public void showLoadingDialog(String msg) {
        if (commonHelp != null) {
            commonHelp.showProgress(msg);
        }
    }

    /**
     * 隐藏dialog
     */
    public void dismissLoadingDialog() {
        if (commonHelp != null) {
            commonHelp.dismissProgress();
        }
    }

    /**
     * 公用失败方法
     *
     * @param msg       提示信息
     * @param isOverdue 当前登录用户是否失效
     */
    public void commonFail(String msg, boolean isOverdue) {
        if (isOverdue) { // 登录失效
            showToastMessage(getResources().getString(R.string.login_Invalid_name));
        } else if (NetworkUtil.isNetworkAvailable(this)) {
            if (!TextUtils.isEmpty(msg)) {
                showToastMessage(msg);
            }
        } else {
            showToastMessage(getResources().getString(R.string.no_network_name));
        }
    }

    /**
     * 公用的弹框
     *
     * @param title      标题
     * @param message    内容
     * @param okListener 确定按钮的监听
     * @param noListener 取消按钮的监听
     */
    public void showCommonAlertDialog(String title, String message, final View.OnClickListener okListener, final View.OnClickListener noListener) {
        if (commonHelp != null) {
            commonHelp.showCommonAlertDialog(title, message, okListener, noListener);
        }
    }

    /**
     * 单个按钮弹框
     *
     * @param title      标题
     * @param message    内容
     * @param okListener 确定按钮的监听
     */
    public void showCommonAlertDialog(String title, String message, final View.OnClickListener okListener) {
        if (commonHelp != null) {
            commonHelp.showCommonAlertDialog(title, message, okListener);
        }
    }

    /**
     * 申请运行时权限
     *
     * @param permissions 申请的权限名
     * @param listener    权限授权的回调
     */
    public void requestRuntimePermission(String[] permissions, PermissionListener listener) {
        permissionListener = listener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }

        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), 1);
        } else {
            permissionListener.onGranted(); // 全部授权
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    List<String> deniedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i]; // 判断是否授权
                        String permission = permissions[i]; // 请求权限的名字
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        }
                    }
                    if (deniedPermissions.isEmpty()) {
                        if (permissionListener != null) {
                            permissionListener.onGranted();
                        }
                    } else {
                        if (permissionListener != null) {
                            permissionListener.onDenied(deniedPermissions);
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (ScreenManager.getScreenManager().goBlackPage()) {
                finish();
            } else {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    showToastMessage(getResources().getString(R.string.home_exit_hint));
                    exitTime = System.currentTimeMillis();
                } else {
                    ScreenManager.getScreenManager().killAllActivity();
                    finish();
                    System.exit(0); // 退出JVM,释放所占内存资源,0表示正常退出
                    android.os.Process.killProcess(android.os.Process.myPid()); // 从系统中kill掉应用程序
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        release();
    }

    /**
     * 释放资源
     */
    private void release() {
        if (commonHelp != null) {
            commonHelp = null;
        }
        if (mButterKnife != null) {
            mButterKnife.unbind();
        }
    }

}
