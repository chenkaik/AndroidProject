package com.example.android.project.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.example.android.project.R;
import com.example.android.project.adapter.BaseFragmentAdapter;
import com.example.android.project.fragment.DataFragment;
import com.example.android.project.fragment.HomeFragment;
import com.example.android.project.fragment.UserFragment;

import butterknife.BindView;

public class MyActivity extends BaseActivity implements ViewPager.OnPageChangeListener,
        BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MyActivity";

    @BindView(R.id.vp_home_pager)
    ViewPager mViewPager;
    @BindView(R.id.bv_home_navigation)
    BottomNavigationView mBottomNavigationView;

    private BaseFragmentAdapter<Fragment> mPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my;
    }

    @Override
    protected void initView() {
        mViewPager.addOnPageChangeListener(this);
        mBottomNavigationView.setItemIconTintList(null); // 不使用图标默认变色
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void initData() {
        mPagerAdapter = new BaseFragmentAdapter<>(this);
        mPagerAdapter.addFragment(HomeFragment.newInstance());
        mPagerAdapter.addFragment(DataFragment.newInstance());
        mPagerAdapter.addFragment(UserFragment.newInstance());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(mPagerAdapter.getCount()); // 限制页面数量
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_home:
//                Logger.e(TAG, "onNavigationItemSelected: 首页");
                //mViewPager.setCurrentItem(0);
                //mViewPager.setCurrentItem(0, false);
                // 如果切换的是相邻之间的 Item 就显示切换动画，如果不是则不要动画
                mViewPager.setCurrentItem(0, mViewPager.getCurrentItem() == 1);
                return true;
            case R.id.home_data:
//                Logger.e(TAG, "onNavigationItemSelected: 数据");
                //mViewPager.setCurrentItem(1);
                //mViewPager.setCurrentItem(1, false);
                mViewPager.setCurrentItem(1, mViewPager.getCurrentItem() == 0 || mViewPager.getCurrentItem() == 2);
                return true;
            case R.id.home_me:
//                Logger.e(TAG, "onNavigationItemSelected: 个人中心");
                //mViewPager.setCurrentItem(2);
                //mViewPager.setCurrentItem(2, false);
//                mViewPager.setCurrentItem(2, mViewPager.getCurrentItem() == 1 || mViewPager.getCurrentItem() == 3);
                mViewPager.setCurrentItem(2, mViewPager.getCurrentItem() == 1);
                return true;
//            case R.id.home_me:
//                //mViewPager.setCurrentItem(3);
//                //mViewPager.setCurrentItem(3, false);
//                mViewPager.setCurrentItem(3, mViewPager.getCurrentItem() == 2);
//                return true;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
//                Logger.e(TAG, "onPageSelected: 首页");
                mBottomNavigationView.setSelectedItemId(R.id.menu_home);
                break;
            case 1:
//                Logger.e(TAG, "onPageSelected: 数据");
                mBottomNavigationView.setSelectedItemId(R.id.home_data);
                break;
            case 2:
//                Logger.e(TAG, "onPageSelected: 个人中心");
                mBottomNavigationView.setSelectedItemId(R.id.home_me);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    protected void onDestroy() {
        mViewPager.removeOnPageChangeListener(this);
        mViewPager.setAdapter(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(null);
        super.onDestroy();
    }

    /**
     * 点击
     * 03-08 23:12:52.876 28214-28214/com.example.android.project E/MyActivity: onNavigationItemSelected: 数据
     * 03-08 23:12:52.878 28214-28214/com.example.android.project E/MyActivity: onPageSelected: 数据
     * 03-08 23:12:52.879 28214-28214/com.example.android.project E/MyActivity: onNavigationItemSelected: 数据
     * 03-08 23:12:55.073 28214-28214/com.example.android.project E/MyActivity: onNavigationItemSelected: 个人中心
     * 03-08 23:12:55.075 28214-28214/com.example.android.project E/MyActivity: onPageSelected: 个人中心
     * 03-08 23:12:55.075 28214-28214/com.example.android.project E/MyActivity: onNavigationItemSelected: 个人中心
     * 03-08 23:12:56.601 28214-28214/com.example.android.project E/MyActivity: onNavigationItemSelected: 首页
     * 03-08 23:12:56.601 28214-28214/com.example.android.project E/MyActivity: onPageSelected: 首页
     * 03-08 23:12:56.601 28214-28214/com.example.android.project E/MyActivity: onNavigationItemSelected: 首页
     *
     * 滑动
     * 03-08 23:13:47.993 28214-28214/com.example.android.project E/MyActivity: onPageSelected: 数据
     * 03-08 23:13:47.993 28214-28214/com.example.android.project E/MyActivity: onNavigationItemSelected: 数据
     * 03-08 23:13:49.674 28214-28214/com.example.android.project E/MyActivity: onPageSelected: 个人中心
     * 03-08 23:13:49.674 28214-28214/com.example.android.project E/MyActivity: onNavigationItemSelected: 个人中心
     * 03-08 23:13:57.026 28214-28214/com.example.android.project E/MyActivity: onPageSelected: 数据
     * 03-08 23:13:57.026 28214-28214/com.example.android.project E/MyActivity: onNavigationItemSelected: 数据
     * 03-08 23:13:57.988 28214-28214/com.example.android.project E/MyActivity: onPageSelected: 首页
     * 03-08 23:13:57.989 28214-28214/com.example.android.project E/MyActivity: onNavigationItemSelected: 首页
     */

}
