package com.fred_w.demo.mvpframedemo.mvp.model.api.cache;

import com.fred_w.demo.mvpframedemo.mvp.model.entity.User;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

/**
 * 自定义公用的 RxCache Provider
 *
 * @author Fred_W
 * @version v1.0.0
 */
public interface CommonCache {

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<List<User>>> getUsers(Observable<List<User>> oUsers,
                                           DynamicKey idLastUserQueired, EvictProvider evictProvider);

}
