package com.qianfeng.day1_retrofit_base.http;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 网络请求接口
 * Created by xray on 16/9/26.
 */
public interface HttpService {

    /**
     * GET中填写URL后部分的路径(从第一个/开始)
     * @return
     */
    @GET("/myWeb/MyServlet")
    Call<String> getName(@Query("name")String name);

    @POST("/myWeb/MyServlet")
    Call<String> postName(@Query("name")String name);
}
