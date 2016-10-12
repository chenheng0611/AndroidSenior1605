package com.qianfeng.day7_mvp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.qianfeng.day7_mvp.R;
import com.qianfeng.day7_mvp.presenter.ILoginPresenter;
import com.qianfeng.day7_mvp.presenter.LoginPresenterImpl;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    private EditText mUsernameEt;
    private EditText mPasswordEt;
    private ILoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        //创建Presenter对象
        mLoginPresenter = new LoginPresenterImpl(this);
    }

    private void initViews() {
        mUsernameEt = (EditText)findViewById(R.id.username_et);
        mPasswordEt = (EditText)findViewById(R.id.password_et);
    }

    public void onLogin(View view) {
        //调用登录方法
        mLoginPresenter.login();
    }

    @Override
    public String getUsername() {
        return mUsernameEt.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPasswordEt.getText().toString();
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFailed() {
        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUsernameEmpty() {
        Toast.makeText(this, "Username can not be empty", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPasswordEmpty() {
        Toast.makeText(this, "Password can not be empty", Toast.LENGTH_SHORT).show();
    }
}
