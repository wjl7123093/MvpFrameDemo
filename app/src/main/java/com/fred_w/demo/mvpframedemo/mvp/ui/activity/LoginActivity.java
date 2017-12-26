package com.fred_w.demo.mvpframedemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.fred_w.demo.mvpframedemo.di.component.DaggerLoginComponent;
import com.fred_w.demo.mvpframedemo.di.module.LoginModule;
import com.fred_w.demo.mvpframedemo.mvp.contract.LoginContract;
import com.fred_w.demo.mvpframedemo.mvp.presenter.LoginPresenter;

import com.fred_w.demo.mvpframedemo.R;
import com.jess.arms.utils.DeviceUtils;


import net.qiujuer.genius.ui.widget.Button;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * LoginActivity 登录界面
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2017-12-19
 * @update 2017-12-25
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.toolbar)
    public Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    public TextView mTvToolbarTitle;
    @BindView(R.id.toolbar_back)
    public RelativeLayout mBtnToolbarBack;

    @BindView(R.id.edtTxtAcc)
    public EditText mEdtTxtAcc;
    @BindView(R.id.edtTxtPwd)
    public EditText mEdtTxtPwd;
    @BindView(R.id.btnLogin)
    public Button mBtnLogin;
    @BindView(R.id.progressBar)
    public ProgressBar mProgressBar;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_login; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
//        setSupportActionBar(mToolbar);
        mBtnToolbarBack.setVisibility(View.GONE);
        mTvToolbarTitle.setText("登录");

        // 登录 lambda
        mBtnLogin.setOnClickListener(v -> {
            mPresenter.validateLogin(mEdtTxtAcc.getText().toString().trim(),
                    mEdtTxtPwd.getText().toString().trim());
            DeviceUtils.hideSoftKeyboard(LoginActivity.this, v);
        });
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);

        // 延迟跳转，为了给 SnackBar 预留显示时间
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ArmsUtils.startActivity(intent);
                killMyself();
            }
        }, 2000);*/

        // lambda
        new Handler().postDelayed(() -> {ArmsUtils.startActivity(intent); killMyself();}, 2000);
    }

    @Override
    public void killMyself() {
        finish();
    }


    @Override
    public void setUsernameError() {
        mEdtTxtAcc.setError("用户名错误");
    }

    @Override
    public void setPasswordError() {
        mEdtTxtPwd.setError("密码错误");
    }
}
