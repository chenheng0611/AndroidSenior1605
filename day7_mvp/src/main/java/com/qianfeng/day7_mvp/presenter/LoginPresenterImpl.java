package com.qianfeng.day7_mvp.presenter;

import android.text.TextUtils;

import com.qianfeng.day7_mvp.model.ILoginCallback;
import com.qianfeng.day7_mvp.model.ILoginModel;
import com.qianfeng.day7_mvp.model.LoginModelImpl;
import com.qianfeng.day7_mvp.view.ILoginView;

/**
 * 主持层的实现类
 * Created by xray on 16/10/9.
 */

public class LoginPresenterImpl implements ILoginPresenter {

    private ILoginModel mLoginModel;
    private ILoginView mLoginView;

    public LoginPresenterImpl(ILoginView view){
        //传递View对象
        mLoginView = view;
        //初始化Model对象
        mLoginModel = new LoginModelImpl();
    }

    private boolean validateInput(){
        if(TextUtils.isEmpty(mLoginView.getUsername())){
            mLoginView.showUsernameEmpty();
            return false;
        }
        if(TextUtils.isEmpty(mLoginView.getPassword())){
            mLoginView.showPasswordEmpty();
            return false;
        }
        return true;
    }

    @Override
    public void login() {
        if(validateInput()){
            mLoginModel.login(
                    mLoginView.getUsername(),
                    mLoginView.getPassword(),
                        new ILoginCallback() {
                        @Override
                        public void success() {
                            mLoginView.loginSuccess();
                        }

                        @Override
                        public void failed() {
                            mLoginView.loginFailed();
                        }
                    });
        }
    }
}
