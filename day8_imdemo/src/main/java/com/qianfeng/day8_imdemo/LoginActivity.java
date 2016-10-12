package com.qianfeng.day8_imdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUsernameEt;
    private EditText mPasswordEt;
    private Button mLoginBtn;
    private Button mRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mUsernameEt = (EditText)findViewById(R.id.login_username_et);
        mPasswordEt = (EditText)findViewById(R.id.login_password_et);
        mLoginBtn = (Button)findViewById(R.id.login_login_btn);
        mRegisterBtn = (Button)findViewById(R.id.login_register_btn);

        mRegisterBtn.setOnClickListener(this);
        mLoginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_register_btn:
                register();
                break;
            case R.id.login_login_btn:
                login();
                break;
        }
    }

    private boolean validateInput(){
        if(TextUtils.isEmpty(mUsernameEt.getText().toString())){
            Toast.makeText(this, "Username can not be empty!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(mPasswordEt.getText().toString())){
            Toast.makeText(this, "Password can not be empty!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void login() {
        if(validateInput()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    EMClient.getInstance().login(mUsernameEt.getText().toString(),
                            mPasswordEt.getText().toString(),new EMCallBack() {//回调
                                @Override
                                public void onSuccess() {
                                    EMClient.getInstance().groupManager().loadAllGroups();
                                    EMClient.getInstance().chatManager().loadAllConversations();
                                    Log.d("main", "登录聊天服务器成功！");
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(LoginActivity.this,FriendActivity.class));
                                        }
                                    });
                                }

                                @Override
                                public void onProgress(int progress, String status) {

                                }

                                @Override
                                public void onError(int code, String message) {
                                    Log.d("main", "登录聊天服务器失败！");
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                            });
                }
            }).start();

        }
    }

    private void register() {
        //注册失败会抛出HyphenateException
        if(validateInput()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        EMClient.getInstance().createAccount(
                                mUsernameEt.getText().toString(),
                                mPasswordEt.getText().toString());//同步方法
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, "Register failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        }
    }
}
