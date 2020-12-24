package com.example.android.project.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * date: 2019/3/8
 * desc: FragmentPagerAdapter 基类
 */
public class BaseFragmentAdapter<F extends Fragment> extends FragmentPagerAdapter {

    private final List<F> mFragmentSet = new ArrayList<>(); // Fragment集合

    private F mCurrentFragment; // 当前显示的Fragment

    public BaseFragmentAdapter(FragmentActivity activity) {
//        this(activity.getSupportFragmentManager());
        this(activity.getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public BaseFragmentAdapter(Fragment fragment) {
        this(fragment.getChildFragmentManager());
    }

    public BaseFragmentAdapter(FragmentManager manager) {
        super(manager);
    }

    private BaseFragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public @NotNull F getItem(int position) {
        return mFragmentSet.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentSet.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) { // 当前页面的主Item
        if (getCurrentFragment() != object) {
            // 记录当前的Fragment对象
            mCurrentFragment = (F) object;
        }
        super.setPrimaryItem(container, position, object);
    }

    public void addFragment(F fragment) {
        mFragmentSet.add(fragment);
    }

    /**
     * 获取Fragment集合
     */
    public List<F> getAllFragment() {
        return mFragmentSet;
    }

    /**
     * 获取当前的Fragment
     */
    public F getCurrentFragment() {
        return mCurrentFragment;
    }

}
