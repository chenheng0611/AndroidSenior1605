package com.qianfeng.day1_retrofit_base.http;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 工具类,用于返回HttpService接口实现对象
 * Created by xray on 16/9/26.
 */
public class HttpUtils {

    public static final String BASE_URL = "http://192.168.57.129:8080";
    private static HttpService sHttpService;

    public static HttpService getHttpService(){
        if(sHttpService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) //设置URL基本地址
                    .addConverterFactory(ScalarsConverterFactory.create())//添加字符串转换器
                    .build();
            sHttpService = retrofit.create(HttpService.class);
        }
        return sHttpService;
    }
}
