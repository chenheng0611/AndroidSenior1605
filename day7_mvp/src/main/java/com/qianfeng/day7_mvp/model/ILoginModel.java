package com.qianfeng.day7_mvp.model;

/**
 * 登录数据模型接口
 * Created by xray on 16/10/9.
 */

public interface ILoginModel {

    /**
     * 登录
     * @param username
     * @param password
     * @param callback
     */
    void login(String username,String password,ILoginCallback callback);
}
