package com.qianfeng.day7_mvp.model;

import android.os.Handler;
import android.os.Message;

/**
 * 登录的实现类
 * Created by xray on 16/10/9.
 */

public class LoginModelImpl  implements ILoginModel{

    private ILoginCallback mLoginCallback;
    public static final int RESULT_SUCCESS = 1;
    public static final int RESULT_FAILED = 2;

    @Override
    public void login(final String username, final String password, ILoginCallback callback) {
        mLoginCallback = callback;
        //模拟网络连接
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //模拟验证
                if("admin".equals(username) && "123456".equals(password)){
                    mHandler.sendEmptyMessage(RESULT_SUCCESS);
                }else{
                    mHandler.sendEmptyMessage(RESULT_FAILED);
                }
            }
        }).start();
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case RESULT_SUCCESS:
                    if(mLoginCallback != null){
                        mLoginCallback.success();
                    }
                    break;
                case RESULT_FAILED:
                    if(mLoginCallback != null){
                        mLoginCallback.failed();
                    }
                    break;
            }
        }
    };
}
