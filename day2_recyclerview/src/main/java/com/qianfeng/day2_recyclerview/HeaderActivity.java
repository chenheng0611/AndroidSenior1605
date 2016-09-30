package com.qianfeng.day2_recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.day2_recyclerview.adapter.HeaderMyAdapter;
import com.qianfeng.day2_recyclerview.adapter.MyAdapter;
import com.qianfeng.day2_recyclerview.adapter.OnItemClickHandler;

import java.util.ArrayList;
import java.util.List;

public class HeaderActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private HeaderMyAdapter mAdapter;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);
        initData();
        initViews();
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mData.add("Item"+i);
        }
    }

    private void initViews() {
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        //设置适配器
        mAdapter = new HeaderMyAdapter(this,mData);
        mRecyclerView.setAdapter(mAdapter);
        //设置布局管理器
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    }
}
