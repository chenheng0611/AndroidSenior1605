package com.qianfeng.day2_recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.day2_recyclerview.adapter.MyAdapter;
import com.qianfeng.day2_recyclerview.adapter.OnItemClickHandler;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LayoutManagerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mData;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_manager);
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
        //添加默认的动画效果
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加监听器事件处理
        mAdapter.setOnItemClickHandler(new OnItemClickHandler() {
            @Override
            public void itemClick(View view, int position) {
                TextView tv = (TextView)view;
                Toast.makeText(LayoutManagerActivity.this, tv.getText().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void itemLongClick(View view, int position) {
                remove(position);
            }
        });
    }


    /**
     * 不同的布局的切换
     * @param view
     */
    public void onChange(View view) {
        switch (view.getId()){
            case R.id.horizontal_btn:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
                break;
            case R.id.vertical_btn:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
                break;
            case R.id.grid_view_btn:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
                break;
            case R.id.pubu_btn:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                break;
            case R.id.insert_btn:
                insert(3,"NewItem");
                break;
            case R.id.remove_btn:
                remove(0);
                break;
        }
    }

    /**
     * 动画效果的插入
     * @param position
     * @param content
     */
    private void insert(int position,String content){
        mData.add(position, content);
        //通知RecyclerView的适配器刷新视图
        //notifyItemXXX 才能实现动画
        mAdapter.notifyItemInserted(position);
    }

    /**
     * 动画效果的删除
     * @param position
     */
    private void remove(int position){
        mData.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
}
