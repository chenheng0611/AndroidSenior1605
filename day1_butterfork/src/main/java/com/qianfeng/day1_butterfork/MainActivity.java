package com.qianfeng.day1_butterfork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.text_view)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterFork.bind(this);

        mTextView.setText("I used ButterFork!!!");
    }

    @OnClick(R.id.text_view)
    void click(){
        Toast.makeText(this, "I used ButterFork!!!", Toast.LENGTH_SHORT).show();
    }
}
