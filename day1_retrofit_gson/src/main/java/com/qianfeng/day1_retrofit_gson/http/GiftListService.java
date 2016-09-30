package com.qianfeng.day1_retrofit_gson.http;

import com.qianfeng.day1_retrofit_gson.bean.GiftListBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by xray on 16/9/26.
 */

public interface GiftListService {

    @GET("/majax.action?method=getGiftList")
    Call<GiftListBean> getGiftList(@Query("pageno")int pageno);
}
