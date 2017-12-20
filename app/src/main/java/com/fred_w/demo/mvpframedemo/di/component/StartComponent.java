package com.fred_w.demo.mvpframedemo.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.fred_w.demo.mvpframedemo.di.module.StartModule;

import com.fred_w.demo.mvpframedemo.mvp.ui.activity.StartActivity;

@ActivityScope
@Component(modules = StartModule.class, dependencies = AppComponent.class)
public interface StartComponent {
    void inject(StartActivity activity);
}