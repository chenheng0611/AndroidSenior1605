package com.qianfeng.day2_coordinatorlayout;

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

    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.anchor_btn:
                intent.setClass(this,AnchorActivity.class);
                break;
            case R.id.appbar_btn:
                intent.setClass(this,AppBarActivity.class);
                break;
            case R.id.toolbar_btn:
                intent.setClass(this,CollapseActivity.class);
                break;
            case R.id.snackbar_btn:
                intent.setClass(this,SnackBarActivity.class);
                break;
            case R.id.behavior_btn:
                intent.setClass(this,BehaviorActivity.class);
                break;
            case R.id.input_btn:
                intent.setClass(this,TextInputActivity.class);
                break;
            case R.id.demo_btn:
                intent.setClass(this,DemoActivity.class);
                break;
        }
        startActivity(intent);
    }
}
