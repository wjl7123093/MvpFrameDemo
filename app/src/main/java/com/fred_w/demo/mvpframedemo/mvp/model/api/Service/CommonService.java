package com.fred_w.demo.mvpframedemo.mvp.model.api.Service;

import com.fred_w.demo.mvpframedemo.mvp.model.entity.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * 自定义公用的 Retrofit Service
 *
 * @author Fred_W
 * @version 1.0.0
 */
public interface CommonService {

    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";

    @Headers({HEADER_API_VERSION})
    @GET("/users")
    Observable<List<User>> getUsers(@Query("since") int lastIdQueried, @Query("per_page") int perPage);

}
