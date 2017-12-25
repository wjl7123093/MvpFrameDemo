package com.fred_w.demo.mvpframedemo.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.fred_w.demo.mvpframedemo.mvp.contract.TwoContract;
import com.fred_w.demo.mvpframedemo.mvp.model.TwoModel;


@Module
public class TwoModule {
    private TwoContract.View view;

    /**
     * 构建TwoModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public TwoModule(TwoContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    TwoContract.View provideTwoView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    TwoContract.Model provideTwoModel(TwoModel model) {
        return model;
    }
}