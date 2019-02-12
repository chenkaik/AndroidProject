package com.android.lib.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;

import java.util.Stack;

/**
 * @date: 2019/1/30
 * @author: Kai
 * @describe: activity统一管理类
 */
public class ScreenManager {

    private static Stack<Activity> activityStack = new Stack<>();

    private ScreenManager() {
    }

    public static ScreenManager getScreenManager() {
        return ScreenManagerHolder.sInstance;
    }

    private static class ScreenManagerHolder {
        private static final ScreenManager sInstance = new ScreenManager();
    }

    /**
     * 获取栈顶Activity（堆栈中最后一个压入的）
     */
    public Activity getTopActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束栈顶Activity（堆栈中最后一个压入的）
     */
    public void killTopActivity() {
        Activity activity = activityStack.lastElement();
        killActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void killActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void killActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                killActivity(activity);
            }
        }
    }

    /**
     * 结束所有栈中的Activity
     */
    public void killAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    @SuppressWarnings("deprecation")
    public void AppExit(Context context) {
        try {
            killAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 弹出栈并finish activity
     */
    public void popActivity() {
        Activity activity = activityStack.pop();
        if (activity != null) {
            activity.finish();
        }
    }

    /**
     * 只弹出栈但不finish
     */
    public void popActivityNoFinish() {
        int size = getSize();
        if (size > 0) {
            activityStack.pop();
        }
    }

    /**
     * 弹出直到是cls类型的，并finish
     *
     * @param cls
     */
    public void popAllActivityExceptOne(Class<?> cls) {
        while (true) {
            Activity activity = activityStack.pop();
            if (activity == null) {
                break;
            }
            if (activity.getClass().equals(cls)) {
                activityStack.remove(activity);
                activity.finish();
                break;
            }
        }

    }

    /**
     * 弹出直到是cls类型的,但并不finish
     *
     * @param cls
     */
    public void popAllNoFinishExceptOne(Class<?> cls) {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            if (activity.getClass().equals(cls)) {
                break;
            }
        }
    }

    /**
     * 清除并finish释放内存
     */
    public void clearAllActivity() {
        int size = getSize();
        boolean isEmpty = activityStack.isEmpty();
        while (size > 0 && !isEmpty) {
            popActivity();
            isEmpty = activityStack.isEmpty();
            size--;
        }
        activityStack.clear();
    }

    /**
     * 清除栈中的内容，但并不finish释放内存
     */
    public void clearAllNoFinish() {
        int size = getSize();
        boolean isEmpty = activityStack.isEmpty();
        while (size > 0 && !isEmpty) {
            popActivityNoFinish();
            isEmpty = activityStack.isEmpty();
            size--;
        }
        activityStack.clear();
    }

    /**
     * 入栈
     *
     * @param activity
     */
    public void pushActivity(Activity activity) {
        activityStack.push(activity);
    }

    /**
     * 获得最近的activity
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 栈的大小
     *
     * @return
     */
    public int getSize() {
        int size = 0;
        size = activityStack.size();
        return size;
    }

    /**
     * 获得第几个元素
     *
     * @param i
     * @return
     */
    public Activity getElement(int i) {
        Activity activity = activityStack.elementAt(i);
        return activity;
    }

    /**
     * 界面跳转
     *
     * @param activity   当前页面
     * @param intent     目标页面
     * @param isPutStack 启动下个是否压入栈中
     */
    public void startPage(Activity activity, Intent intent, boolean isPutStack) {
        if (activity == null || intent == null) {
            return;
        }
        activity.startActivity(intent);
        if (isPutStack) {
            pushActivity(activity);
        } else {
            activity.finish();
        }
    }

    /**
     * 界面跳转
     *
     * @param activity    当前页面
     * @param intent      目标页面
     * @param isPutStack  启动下个是否压入栈中
     * @param requestCode 请求code
     */
    public void startPage(Activity activity, Intent intent, boolean isPutStack, int requestCode) {
        if (activity == null || intent == null) {
            return;
        }
        activity.startActivityForResult(intent, requestCode);
        if (isPutStack) {
            pushActivity(activity);
        } else {
            activity.finish();
        }
    }

    /**
     * 返回上个界面
     *
     * @return 栈中是否有界面, 有, 返回true; 没有,返回false
     */
    public boolean goBlackPage() {
        boolean re = false;
        int size = getSize();
        boolean isEmpty = true;
        isEmpty = activityStack.isEmpty();
        if (size > 0 && !isEmpty) {
            popActivityNoFinish();
            re = true;
        }
        return re;
    }

}
