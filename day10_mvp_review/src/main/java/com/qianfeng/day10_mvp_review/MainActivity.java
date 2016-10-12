package com.qianfeng.day10_mvp_review;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qianfeng.day10_mvp_review.openserver.view.OpenServerFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content_layout,new OpenServerFragment())
                .commit();
    }
}
