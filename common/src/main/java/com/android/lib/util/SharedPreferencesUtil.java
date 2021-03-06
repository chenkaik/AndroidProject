package com.android.lib.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * date: 2019/1/30.
 * desc: 保存用户信息
 * SharedPreference 相关修改使用 apply 方法进行提交会先写入内存，然后异步写入
 * 磁盘，commit 方法是直接写入磁盘。如果频繁操作的话 apply 的性能会优于 commit，
 * apply 会将最后修改内容写入磁盘。但是如果希望立刻获取存储操作的结果，并据此
 * 做相应的其他操作，应当使用 commit
 */
public class SharedPreferencesUtil {

    private String TAG = getClass().getSimpleName();
    // 存放信息的文件名
    private SharedPreferences mSp;

    public SharedPreferencesUtil(Context context, String name) {
        mSp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    interface Executable {
        void execute(Editor editor);
    }

    private boolean executeWithEditor(Executable ex) {
        Editor editor = mSp.edit();
        ex.execute(editor);
        return editor.commit();
    }

    public boolean put(final String key, final boolean value) {
        return executeWithEditor(editor -> editor.putBoolean(key, value));
    }

    public boolean put(final String key, final int value) {
        return executeWithEditor(editor -> editor.putInt(key, value));
    }

    public boolean put(final String key, final long value) {
        return executeWithEditor(editor -> editor.putLong(key, value));
    }

    public boolean put(final String key, final float value) {
        return executeWithEditor(editor -> editor.putFloat(key, value));
    }

    public boolean put(final String key, final String value) {
        return executeWithEditor(editor -> editor.putString(key, value));
    }

    public void remove(final String key) {
        executeWithEditor(editor -> editor.remove(key));
    }

    public void clear() {
        executeWithEditor(editor -> editor.clear());
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return mSp.getBoolean(key, defaultValue);
    }

    public int getInt(String key, int defaultValue) {
        return mSp.getInt(key, defaultValue);
    }

    public long getLong(String key, long defaultValue) {
        return mSp.getLong(key, defaultValue);
    }

    public float getFloat(String key, float defaultValue) {
        return mSp.getFloat(key, defaultValue);
    }

    public String getString(String key, String defaultValue) {
        return mSp.getString(key, defaultValue);
    }

    public SharedPreferences getSharedPreferences() {
        return mSp;
    }

    public Editor getEditor() {
        return mSp.edit();
    }
}
