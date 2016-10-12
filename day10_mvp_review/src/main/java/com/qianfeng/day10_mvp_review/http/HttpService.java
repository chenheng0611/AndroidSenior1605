package com.qianfeng.day10_mvp_review.http;


import com.qianfeng.day10_mvp_review.bean.OpenServerBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by xray on 16/10/9.
 */

public interface HttpService {

    //返回值使用RXJava的观察者
    @GET("/majax.action?method=getJtkaifu")
    Observable<OpenServerBean> getOpenServerInfo();
}
