package com.example.android.project.activity;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.android.project.R;
import com.example.android.project.adapter.BaseFragmentAdapter;
import com.example.android.project.databinding.ActivityMainBinding;
import com.example.android.project.fragment.DataFragment;
import com.example.android.project.fragment.FindFragment;
import com.example.android.project.fragment.HomeFragment;
import com.example.android.project.fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener,
        BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";

//    @BindView(R.id.vp_home_pager)
    private ViewPager mViewPager;
//    @BindView(R.id.bv_home_navigation)
//    BottomNavigationView mBottomNavigationView;

    private BaseFragmentAdapter<Fragment> mPagerAdapter;
    private TextView mBadgeView;
    private ActivityMainBinding mActivityMainBinding;

    //    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_main;
//    }

    @Override
    protected void initView() {
        mActivityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mActivityMainBinding.getRoot());
        mViewPager = mActivityMainBinding.vpHomePager;
        mViewPager.addOnPageChangeListener(this);
        mActivityMainBinding.bvHomeNavigation.setItemIconTintList(null); // 不使用图标默认变色
//        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        mActivityMainBinding.bvHomeNavigation.setOnNavigationItemSelectedListener(this);
//        mBottomNavigationView.setItemIconSize(55); // 设置菜单项图标的大小
        mActivityMainBinding.bvHomeNavigation.getMenu().getItem(1).setChecked(true);
        // 添加角标
        // 获取整个的BottomNavigationMenuView
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) mActivityMainBinding.bvHomeNavigation.getChildAt(0);
        // 获取所添加的Tab或者叫Menu
        View tab = menuView.getChildAt(1);
        BottomNavigationItemView itemView = (BottomNavigationItemView) tab;
        // 加载角标View
        View badge = LayoutInflater.from(this).inflate(R.layout.badge_layout, menuView, false);
        // 添加到Tab上
        itemView.addView(badge);
        mBadgeView = badge.findViewById(R.id.badge_number);
        mBadgeView.setText("99+");
//        mBadgeView.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        mPagerAdapter = new BaseFragmentAdapter<>(this);
        mPagerAdapter.addFragment(HomeFragment.newInstance());
        mPagerAdapter.addFragment(DataFragment.newInstance());
        mPagerAdapter.addFragment(FindFragment.newInstance());
        mPagerAdapter.addFragment(UserFragment.newInstance());
        mActivityMainBinding.vpHomePager.setAdapter(mPagerAdapter);
        mActivityMainBinding.vpHomePager.setOffscreenPageLimit(mPagerAdapter.getCount()); // 限制页面数量
        mActivityMainBinding.vpHomePager.setCurrentItem(1);
    }


    //    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        switch (menuItem.getItemId()) {
//            case R.id.menu_home:
////                Logger.e(TAG, "onNavigationItemSelected: 首页");
//                //mViewPager.setCurrentItem(0);
//                //mViewPager.setCurrentItem(0, false);
//                // 如果切换的是相邻之间的 Item 就显示切换动画，如果不是则不要动画
//                mActivityMainBinding.vpHomePager.setCurrentItem(0, mActivityMainBinding.vpHomePager.getCurrentItem() == 1);
//                return true;
//            case R.id.home_data:
////                Logger.e(TAG, "onNavigationItemSelected: 数据");
//                mActivityMainBinding.vpHomePager.setCurrentItem(1, mActivityMainBinding.vpHomePager.getCurrentItem() == 0 || mActivityMainBinding.vpHomePager.getCurrentItem() == 2);
//                return true;
//            case R.id.home_find:
////                Logger.e(TAG, "onNavigationItemSelected: 个人中心");
//                mActivityMainBinding.vpHomePager.setCurrentItem(2, mActivityMainBinding.vpHomePager.getCurrentItem() == 1 || mActivityMainBinding.vpHomePager.getCurrentItem() == 3);
////                mViewPager.setCurrentItem(2, mViewPager.getCurrentItem() == 1);
//                return true;
//            case R.id.home_me:
//                mActivityMainBinding.vpHomePager.setCurrentItem(3, mActivityMainBinding.vpHomePager.getCurrentItem() == 2);
//                return true;
//        }
//        return false;
        int itemId = menuItem.getItemId();
        if (itemId == R.id.menu_home) {
            mActivityMainBinding.vpHomePager.setCurrentItem(0, mActivityMainBinding.vpHomePager.getCurrentItem() == 1);
            return true;
        } else if (itemId == R.id.home_data) {
            mActivityMainBinding.vpHomePager.setCurrentItem(1, mActivityMainBinding.vpHomePager.getCurrentItem() == 0 || mActivityMainBinding.vpHomePager.getCurrentItem() == 2);
            return true;
        } else if (itemId == R.id.home_find) {
            mActivityMainBinding.vpHomePager.setCurrentItem(2, mActivityMainBinding.vpHomePager.getCurrentItem() == 1 || mActivityMainBinding.vpHomePager.getCurrentItem() == 3);
            return true;
        } else if (itemId == R.id.home_me) {
            mActivityMainBinding.vpHomePager.setCurrentItem(3, mActivityMainBinding.vpHomePager.getCurrentItem() == 2);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
//                Logger.e(TAG, "onPageSelected: 首页");
                mActivityMainBinding.bvHomeNavigation.setSelectedItemId(R.id.menu_home);
                break;
            case 1:
//                Logger.e(TAG, "onPageSelected: 数据");
                mActivityMainBinding.bvHomeNavigation.setSelectedItemId(R.id.home_data);
                break;
            case 2:
//                Logger.e(TAG, "onPageSelected: 个人中心");
                mActivityMainBinding.bvHomeNavigation.setSelectedItemId(R.id.home_find);
                break;
            case 3:
                mActivityMainBinding.bvHomeNavigation.setSelectedItemId(R.id.home_me);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    protected void onDestroy() {
        mActivityMainBinding.vpHomePager.removeOnPageChangeListener(this);
        mActivityMainBinding.vpHomePager.setAdapter(null);
        mActivityMainBinding.bvHomeNavigation.setOnNavigationItemSelectedListener(null);
        mPagerAdapter = null;
        super.onDestroy();
    }

    /**
     * setSelectedItemId(int itemId) 设置选择的菜单项 ID。
     * setElevation(float elevation) 设置此视图的基本高程（以像素为单位）。
     * setItemBackground(Drawable background) 将菜单项的背景设置为给定的可绘制对象。
     * setItemBackgroundResource(int resId) 将菜单项的背景设置为给定资源。
     * setItemIconSize(int iconSize) 设置大小以提供菜单项图标。
     * setItemIconTintList(ColorStateList tint) 设置应用于菜单项图标的色彩。
     * setItemRippleColor(ColorStateList itemRippleColor) 将菜单项的背景设置为具有给定颜色的波纹。
     * setItemTextAppearanceActive(int textAppearanceRes) 设置用于菜单项标签的文本样式。
     * setItemTextAppearanceInactive(int textAppearanceRes) 设置用于非活动菜单项标签的文本样式。
     * setItemTextColor(ColorStateList textColor) 设置颜色以用于菜单项文本的不同状态（正常，选中，聚焦等）。
     * setLabelVisibilityMode(int labelVisibilityMode) 设置导航项目的标签可见性模式。
     * setItemHorizontalTranslationEnabled(boolean itemHorizontalTranslationEnabled) 设置当合并的项目宽度填满屏幕时，菜单项是否在选择时水平平移。
     * setOnNavigationItemReselectedListener(BottomNavigationView.OnNavigationItemReselectedListener listener) 设置一个侦听器，当重新选择当前选择的底部导航项时将通知该侦听器。
     * setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener listener) 设置一个侦听器，当选择底部导航项时将通知该侦听器。
     * getMenu() 返回 Menu 与此底部导航栏关联的实例。
     * getMaxItemCount() 返回最大 Menu 数量。
     *
     * 作者：Jaynm
     * 链接：https://juejin.im/post/5f1fed2cf265da22d26bb13a
     * 来源：掘金
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

}
