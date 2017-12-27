package com.fred_w.demo.mvpframedemo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.fred_w.demo.mvpframedemo.app.utils.FragmentUtils;
import com.fred_w.demo.mvpframedemo.di.component.DaggerMainComponent;
import com.fred_w.demo.mvpframedemo.mvp.ui.fragment.FourFragment;
import com.fred_w.demo.mvpframedemo.mvp.ui.fragment.OneFragment;
import com.fred_w.demo.mvpframedemo.mvp.ui.fragment.ThreeFragment;
import com.fred_w.demo.mvpframedemo.mvp.ui.fragment.TwoFragment;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.fred_w.demo.mvpframedemo.di.component.MainComponent;
import com.fred_w.demo.mvpframedemo.di.module.MainModule;
import com.fred_w.demo.mvpframedemo.mvp.contract.MainContract;
import com.fred_w.demo.mvpframedemo.mvp.presenter.MainPresenter;

import com.fred_w.demo.mvpframedemo.R;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.fred_w.demo.mvpframedemo.app.EventBusTags.ACTIVITY_FRAGMENT_REPLACE;
import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * MainActivity 主界面
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2017-12-19
 * @upadte 2017-12-25
 */
@Route(path = "/test/main")
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View,
        BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.main_frame)
    FrameLayout mFrameMain;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;

    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private FourFragment fourFragment;

    private List<Fragment> mFragments;
    private List<Integer> mNavIds;
    private int mReplace = 0;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        /*** the setting for BadgeItem ***/
        BadgeItem badgeItem = new BadgeItem();
        badgeItem.setHideOnSelect(false)
                .setText("10")
                .setBackgroundColorResource(R.color.red_500)
                .setBorderWidth(0);

        /*** the setting for BottomNavigationBar ***/

        /** 样式 1 */
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setBarBackgroundColor(R.color.white);//set background color for navigation bar
        mBottomNavigationBar.setInActiveColor(R.color.grey_600);//unSelected icon color
        mBottomNavigationBar.setActiveColor(R.color.colorPrimary); // selected icon color
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_home, R.string.tab_one).setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.ic_category, R.string.tab_two))
                .addItem(new BottomNavigationItem(R.mipmap.ic_discovery, R.string.tab_three))
                .addItem(new BottomNavigationItem(R.mipmap.ic_mine, R.string.tab_four))
                .setFirstSelectedPosition(0)
                .initialise();

        /** 样式 2 */
        /*mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        mBottomNavigationBar.setBarBackgroundColor(R.color.white);//set background color for navigation bar
        mBottomNavigationBar.setInActiveColor(R.color.grey_800);//unSelected icon color
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.icon_one, R.string.tab_one).setActiveColorResource(R.color.green).setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.drawable.icon_two, R.string.tab_two).setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.icon_three, R.string.tab_three).setActiveColorResource(R.color.lime))
                .addItem(new BottomNavigationItem(R.drawable.icon_four, R.string.tab_four))
                .setFirstSelectedPosition(0)
                .initialise();*/

        mBottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment(savedInstanceState);
    }

    private void setDefaultFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            oneFragment = OneFragment.newInstance();
            twoFragment = TwoFragment.newInstance();
            threeFragment = ThreeFragment.newInstance();
            fourFragment = FourFragment.newInstance();
        } else {
            mReplace = savedInstanceState.getInt(ACTIVITY_FRAGMENT_REPLACE);
            FragmentManager fm = getSupportFragmentManager();
            oneFragment = (OneFragment) FragmentUtils.findFragment(fm, OneFragment.class);
            twoFragment = (TwoFragment) FragmentUtils.findFragment(fm, TwoFragment.class);
            threeFragment = (ThreeFragment) FragmentUtils.findFragment(fm, ThreeFragment.class);
            fourFragment = (FourFragment) FragmentUtils.findFragment(fm, FourFragment.class);
        }
        if (mFragments == null) {
            mFragments = new ArrayList<>();
            mFragments.add(oneFragment);
            mFragments.add(twoFragment);
            mFragments.add(threeFragment);
            mFragments.add(fourFragment);
        }
        FragmentUtils.addFragments(getSupportFragmentManager(), mFragments, R.id.main_frame, 0);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }


    @Override
    public void onTabSelected(int position) {
        mReplace = position;
        FragmentUtils.hideAllShowFragment(mFragments.get(mReplace));
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    /**
     * 说明：
     * 1、onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState)
     *    该方法只针对 API 21(Android 5.0) 以上才有 PersistableBundle 参数
     * 2、如果使用上述方法，在 API 21(Android 5.0) 以下机型会报如下错误：
     *    java.lang.ClassNotFoundException: Didn't find class "android.os.PersistableBundle" on path: DexPathList
     *    原因就是因为 低版本(<21) 没有 PersistableBundle 这个 class
     * 3、综合以上两点，从 兼容性 方面考虑，故只调用包含一个参数的方法，即
     *    onSaveInstanceState(Bundle outState)
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //保存当前Activity显示的Fragment索引
        outState.putInt(ACTIVITY_FRAGMENT_REPLACE, mReplace);
    }

}
