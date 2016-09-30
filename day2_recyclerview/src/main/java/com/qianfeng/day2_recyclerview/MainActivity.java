package com.qianfeng.day2_recyclerview;

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
            case R.id.base_btn:
                intent.setClass(this,RecyclerViewBaseActivity.class);
                break;
            case R.id.layout_btn:
                intent.setClass(this,LayoutManagerActivity.class);
                break;
            case R.id.header_btn:

                intent.setClass(this,HeaderActivity.class);
                break;
        }
        startActivity(intent);
    }
}
