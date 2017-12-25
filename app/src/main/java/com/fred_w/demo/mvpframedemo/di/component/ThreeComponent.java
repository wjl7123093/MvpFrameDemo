package com.fred_w.demo.mvpframedemo.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.fred_w.demo.mvpframedemo.di.module.ThreeModule;

import com.fred_w.demo.mvpframedemo.mvp.ui.fragment.ThreeFragment;

@ActivityScope
@Component(modules = ThreeModule.class, dependencies = AppComponent.class)
public interface ThreeComponent {
    void inject(ThreeFragment fragment);
}