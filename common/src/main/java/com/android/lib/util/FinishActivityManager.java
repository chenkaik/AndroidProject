package com.android.lib.util;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Stack;

/**
 * date: 2019/4/2
 * desc: 管理activity
 * 在BaseActivity中onCreate()中addActivity
 * 在onDestroy()中finishActivity()
 * 其他功能在各个子Activity中根据需要添加
 * https://juejin.im/post/5ca1844151882543e31e27fa#heading-5
 */
public final class FinishActivityManager {

    private FinishActivityManager() {
    }

    //    private static FinishActivityManager sManager;
    private Stack<WeakReference<Activity>> mActivityStack;

//    public static FinishActivityManager getManager() {
//        if (sManager == null) {
//            synchronized (FinishActivityManager.class) {
//                if (sManager == null) {
//                    sManager = new FinishActivityManager();
//                }
//            }
//        }
//        return sManager;
//    }

    public static FinishActivityManager getManager() {
        return FinishActivityManagerHolder.sInstance;
    }

    private static class FinishActivityManagerHolder {
        private static final FinishActivityManager sInstance = new FinishActivityManager();
    }

    /**
     * 添加activity到集合中
     *
     * @param activity currentActivity
     */
    public void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<>();
        }
        mActivityStack.add(new WeakReference<>(activity));
    }

    /**
     * 检查弱引用是否释放，若释放，则从栈中清理掉该元素
     */
    private void checkWeakReference() {
        if (mActivityStack != null) {
            for (Iterator<WeakReference<Activity>> it = mActivityStack.iterator(); it.hasNext(); ) {
                WeakReference<Activity> activityWeakReference = it.next();
                Activity temp = activityWeakReference.get();
                if (temp == null) {
                    it.remove();
                }
            }
        }
    }

    /**
     * 获取当前的Activity(栈中最后一个压入的)
     *
     * @return
     */
    public Activity currentActivity() {
        checkWeakReference();
        if (mActivityStack != null && !mActivityStack.isEmpty()) {
            return mActivityStack.lastElement().get();
        }
        return null;
    }

    /**
     * 关闭当前的activity(栈中最后一个压入的)
     */
    public void finishActivity() {
        Activity activity = currentActivity();
        if (activity != null) {
            finishActivity(activity);
        }
    }

    /**
     * 关闭指定的Activity
     *
     * @param activity 指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null && mActivityStack != null) {
            // 使用迭代器进行安全删除
            for (Iterator<WeakReference<Activity>> it = mActivityStack.iterator(); it.hasNext(); ) {
                WeakReference<Activity> weakReference = it.next();
                Activity temp = weakReference.get();
                if (temp == null) {
                    it.remove();
                    continue;
                }
                if (temp == activity) {
                    it.remove();
                }
            }
            activity.finish();
        }
    }

    /**
     * 关闭指定类名的所有activity
     *
     * @param cls 关闭的activity
     */
    public void finishActivity(Class<?> cls) {
        if (mActivityStack != null) {
            // 使用迭代器进行安全删除
            for (Iterator<WeakReference<Activity>> it = mActivityStack.iterator(); it.hasNext(); ) {
                WeakReference<Activity> activityWeakReference = it.next();
                Activity activity = activityWeakReference.get();
                // 清理掉已经释放的activity
                if (activity == null) {
                    it.remove();
                    continue;
                }
                if (activity.getClass().equals(cls)) {
                    it.remove();
                    activity.finish();
                }
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        if (mActivityStack != null) {
            for (WeakReference<Activity> activityWeakReference : mActivityStack) {
                Activity activity = activityWeakReference.get();
                if (activity != null) {
                    activity.finish();
                }
            }
            mActivityStack.clear();
        }
    }

    /**
     * 退出应用程序
     */
    public void exitApp() {
        try {
            finishAllActivity();
            // 退出JVM,释放所占内存资源,0表示正常退出
            System.exit(0);
            // 从系统中kill掉应用程序
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
