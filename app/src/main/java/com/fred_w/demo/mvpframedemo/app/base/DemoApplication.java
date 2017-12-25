package com.fred_w.demo.mvpframedemo.app.base;

import com.fred_w.demo.mvpframedemo.app.utils.CrashHandler;
import com.jess.arms.base.BaseApplication;

/**
 * 全局 Application
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2017-12-25
 */
public class DemoApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        // 全局异常捕捉
        CrashHandler catchHandler = CrashHandler.getInstance();
        catchHandler.init(getApplicationContext());

    }

}
