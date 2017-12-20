package com.fred_w.demo.mvpframedemo.mvp.presenter;

import android.app.Application;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.widget.Toast;

import com.fred_w.demo.mvpframedemo.app.utils.encrypt.RSAUtil;
import com.fred_w.demo.mvpframedemo.mvp.model.api.Api;
import com.fred_w.demo.mvpframedemo.mvp.model.entity.BaseJson;
import com.fred_w.demo.mvpframedemo.mvp.model.entity.User;
import com.fred_w.demo.mvpframedemo.mvp.ui.activity.MainActivity;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import sun.misc.BASE64Encoder;

import javax.inject.Inject;

import com.fred_w.demo.mvpframedemo.mvp.contract.LoginContract;
import com.jess.arms.utils.RxLifecycleUtils;

/**
 * LoginPresenter(P)
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2017-12-19
 */
@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    /**
     * 验证 登录
     * @param username
     * @param passwrod
     */
    public void validateLogin(String username, String passwrod) {
        if (TextUtils.isEmpty(username)) {
            mRootView.setUsernameError();
            return;
        } else if (TextUtils.isEmpty(passwrod)) {
            mRootView.setPasswordError();
            return;
        }

        String loginData = String.format("{\"username\":\"%1$s\","
                + "\"password\":\"%2$s\"}", username, passwrod);
        String data = "";
        try {
            data = encrypt(loginData);
        } catch (Exception e) {
            mRootView.showMessage(e.getMessage());
            e.printStackTrace();
        }

        // RxJava 链式事物流执行 [登录] 操作
        mModel.loginTest(Api.APP_ID, data)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))    // 重试机制，第一个参数重试次数，第二个重试间隔
                .doOnSubscribe(disposable -> {
                    // Action onSubscriber
                    mRootView.showLoading();
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> {
                    // Action onFinally
                    mRootView.hideLoading();
//                    mRootView.launchActivity(new Intent(mApplication.getApplicationContext(), MainActivity.class));
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView)) //使用Rxlifecycle,使Disposable和Activity一起销毁
                .subscribe(new ErrorHandleSubscriber<BaseJson<User>>(mErrorHandler) {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.onSubscribe(d);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        mRootView.launchActivity(new Intent(mApplication.getApplicationContext(), MainActivity.class));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);
                        mRootView.showMessage("登陆失败");
                    }

                    @Override
                    public void onNext(@NonNull BaseJson<User> userBaseJson) {
                        if (userBaseJson.isSuccess()) {
                            mRootView.showMessage("登陆成功");
                        } else {
                            mRootView.showMessage("登陆失败");
                        }
                    }
                });
    }

    /**
     * RSA 加密
     * @param data 加密字符串
     * @return
     * @throws Exception
     */
    private String encrypt(String data) throws Exception {
        byte[] bt_cipher = RSAUtil.encrypt(RSAUtil.getPublicKey(
                Api.APP_SECRET), data.getBytes());
        String encryptStr = new BASE64Encoder().encodeBuffer(bt_cipher);
        System.out.println("加密后："+ encryptStr);
        return encryptStr;
    }

}

