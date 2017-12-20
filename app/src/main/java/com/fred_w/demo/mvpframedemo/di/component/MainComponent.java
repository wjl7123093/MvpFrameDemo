package com.fred_w.demo.mvpframedemo.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.fred_w.demo.mvpframedemo.di.module.MainModule;

import com.fred_w.demo.mvpframedemo.mvp.ui.activity.MainActivity;
import com.fred_w.demo.mvpframedemo.mvp.ui.fragment.MainFragment;

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);

    void inject(MainFragment fragment);
}