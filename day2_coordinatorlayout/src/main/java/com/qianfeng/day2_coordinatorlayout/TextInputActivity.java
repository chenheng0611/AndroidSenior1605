package com.qianfeng.day2_coordinatorlayout;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

public class TextInputActivity extends AppCompatActivity {

    private TextInputLayout mUsernameLayout;
    private TextInputLayout mPasswordLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input);

        initViews();
    }

    private void initViews() {
        mUsernameLayout = (TextInputLayout)findViewById(R.id.username_et_layout);
        mPasswordLayout = (TextInputLayout)findViewById(R.id.password_et_layout);
    }

    public void onSubmit(View view) {
        String username = mUsernameLayout.getEditText().getText().toString();
        String password = mPasswordLayout.getEditText().getText().toString();
        if(TextUtils.isEmpty(username)){
            mUsernameLayout.setError("Username can not be empty!");
        }else{
            mUsernameLayout.setErrorEnabled(false);
        }
        if(TextUtils.isEmpty(password)){
            mPasswordLayout.setError("Password can not be empty!");
        }else{
            mPasswordLayout.setErrorEnabled(false);
        }
    }


}
