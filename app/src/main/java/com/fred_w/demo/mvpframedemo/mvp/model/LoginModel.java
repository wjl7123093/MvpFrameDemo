package com.fred_w.demo.mvpframedemo.mvp.model;

import android.app.Application;

import com.fred_w.demo.mvpframedemo.mvp.model.api.Service.LoginService;
import com.fred_w.demo.mvpframedemo.mvp.model.entity.BaseJson;
import com.fred_w.demo.mvpframedemo.mvp.model.entity.User;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.fred_w.demo.mvpframedemo.mvp.contract.LoginContract;

import io.reactivex.Observable;

/**
 * LoginModel(M)
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2017-12-19
 */
@ActivityScope
public class LoginModel extends BaseModel implements LoginContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public LoginModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<User> login(String username, String password) {
        return mRepositoryManager.obtainRetrofitService(LoginService.class)
                .login(username, password);
    }

    @Override
    public Observable<BaseJson<User>> loginTest(String appid, String data) {
        return mRepositoryManager.obtainRetrofitService(LoginService.class)
                .loginTest(appid, data);
    }
}