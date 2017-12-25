package com.fred_w.demo.mvpframedemo.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.fred_w.demo.mvpframedemo.di.module.FourModule;

import com.fred_w.demo.mvpframedemo.mvp.ui.fragment.FourFragment;

@ActivityScope
@Component(modules = FourModule.class, dependencies = AppComponent.class)
public interface FourComponent {
    void inject(FourFragment fragment);
}