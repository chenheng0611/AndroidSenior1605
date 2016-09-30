package com.qianfeng.day1_retrofit_gson.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xray on 16/9/26.
 */

public class HttpUtils {

    public static final String BASE_URL = "http://www.1688wan.com";
    private static GiftListService sGiftListService;


    public static GiftListService getGiftListService(){
        if(sGiftListService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            sGiftListService = retrofit.create(GiftListService.class);
        }
        return sGiftListService;
    }
}
