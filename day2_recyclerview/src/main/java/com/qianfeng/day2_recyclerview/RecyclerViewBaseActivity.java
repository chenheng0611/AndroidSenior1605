package com.qianfeng.day2_recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qianfeng.day2_recyclerview.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.导入RecyclerView的库
 * 2.继承RecyclerView.ViewHolder类
 * 3.继承RecyclerView.Adapter类
 * 4.给RecyclerView设置适配器
 * 5.给RecyclerView设置布局管理器
 */
public class RecyclerViewBaseActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mData;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_base);
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
        mAdapter = new MyAdapter(this,mData);
        mRecyclerView.setAdapter(mAdapter);
        //设置布局管理器
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

}
