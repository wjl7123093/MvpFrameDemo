package com.fred_w.demo.mvpframedemo.mvp.model.api.service;

import com.fred_w.demo.mvpframedemo.mvp.model.entity.BaseJson;
import com.fred_w.demo.mvpframedemo.mvp.model.entity.User;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 登录接口
 *
 * @author Fred_W
 * @version v1.0.0
 */
public interface LoginService {

    @FormUrlEncoded
    @POST("api/login")
    Observable<User> login(@Field("username") String uername, @Field("password") String password);

    @FormUrlEncoded
    @POST("api/index/login")
    Observable<BaseJson<User>> loginTest(@Field("app_id") String appid, @Field("user") String data);

}
