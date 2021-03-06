package com.fred_w.demo.mvpframedemo.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.fred_w.demo.mvpframedemo.di.module.TwoModule;

import com.fred_w.demo.mvpframedemo.mvp.ui.fragment.TwoFragment;

@ActivityScope
@Component(modules = TwoModule.class, dependencies = AppComponent.class)
public interface TwoComponent {
    void inject(TwoFragment fragment);
}