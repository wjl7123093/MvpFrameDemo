package com.fred_w.demo.mvpframedemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.fred_w.demo.mvpframedemo.di.component.DaggerStartComponent;
import com.fred_w.demo.mvpframedemo.di.module.StartModule;
import com.fred_w.demo.mvpframedemo.mvp.contract.StartContract;
import com.fred_w.demo.mvpframedemo.mvp.presenter.StartPresenter;

import com.fred_w.demo.mvpframedemo.R;


import static com.jess.arms.utils.Preconditions.checkNotNull;


public class StartActivity extends BaseActivity<StartPresenter> implements StartContract.View {


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerStartComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .startModule(new StartModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_start; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {

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


}
