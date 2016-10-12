package com.qianfeng.day7_obserber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.qianfeng.day7_obserber.views.MyEditText;
import com.qianfeng.day7_obserber.views.MyTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        MyEditText editText = (MyEditText)findViewById(R.id.edit_text);
        MyTextView textView1 = (MyTextView)findViewById(R.id.text1);
        MyTextView textView2 = (MyTextView)findViewById(R.id.text2);
        MyTextView textView3 = (MyTextView)findViewById(R.id.text3);
        //注册
        editText.addObserver(textView1);
        editText.addObserver(textView2);
        editText.addObserver(textView3);
    }
}
