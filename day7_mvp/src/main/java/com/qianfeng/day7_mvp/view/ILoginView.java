package com.qianfeng.day7_mvp.view;

/**
 * 登录视图层接口
 * Created by xray on 16/10/9.
 */

public interface ILoginView {

    String getUsername();
    String getPassword();
    void loginSuccess();
    void loginFailed();
    void showUsernameEmpty();
    void showPasswordEmpty();
}
