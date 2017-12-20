package com.fred_w.demo.mvpframedemo.mvp.contract;

import com.fred_w.demo.mvpframedemo.mvp.model.entity.BaseJson;
import com.fred_w.demo.mvpframedemo.mvp.model.entity.User;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import io.reactivex.Observable;

/**
 * LoginContract(IV & IM)
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2017-12-19
 */
public interface LoginContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void setUsernameError();
        void setPasswordError();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        // 登录
        Observable<User> login(String username, String password);
        // 登录
        Observable<BaseJson<User>> loginTest(String appid, String data);
    }
}
