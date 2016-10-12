package com.qianfeng.day7_retrofit_rxjava_openserver.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xray on 16/10/9.
 */

public class HttpUtils {

    public static final String BASE_URL = "http://www.1688wan.com";
    private static HttpService sHttpService;

    public static HttpService getHttpService(){
        if(sHttpService == null){
            sHttpService = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    //添加RxJava支持的库
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()
                    .create(HttpService.class);
        }
        return  sHttpService;
    }
}
