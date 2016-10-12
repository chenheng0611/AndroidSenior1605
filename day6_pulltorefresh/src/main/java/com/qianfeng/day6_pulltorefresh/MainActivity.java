package com.qianfeng.day6_pulltorefresh;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshListView mListView;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mListView = (PullToRefreshListView)findViewById(R.id.list_view);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("String "+ i);
        }
        mAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                list);
        //设置适配器
        mListView.setAdapter(mAdapter);
        //设置刷新模式,BOTH代表上拉、下拉都有
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        //设置刷新监听
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            //下拉刷新
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new Handler()
                        .postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //请求网络进行刷新
                                Toast.makeText(MainActivity.this, "下拉刷新", Toast.LENGTH_SHORT).show();
                                //停止刷新
                                mListView.onRefreshComplete();
                            }
                        },1000);
            }

            //上拉加载
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new Handler()
                        .postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //请求网络进行刷新
                                Toast.makeText(MainActivity.this, "加载更多", Toast.LENGTH_SHORT).show();
                                //停止刷新
                                mListView.onRefreshComplete();
                            }
                        },1000);
            }
        });
        //获得原版的ListView
        ListView refreshableView = mListView.getRefreshableView();
        //添加头部视图
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.ic_launcher);
        refreshableView.addHeaderView(imageView);
    }
}
