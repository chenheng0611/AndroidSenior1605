package com.qianfeng.day5_recorder_player;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onRecord(View view) {
        startActivity(new Intent(this,RecorderActivity.class));
    }

    public void onPlay(View view) {
        startActivity(new Intent(this,PlayerActivity.class));
    }
}
