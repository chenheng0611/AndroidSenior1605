package com.qianfeng.day7_retrofit_rxjava_openserver;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.qianfeng.day7_retrofit_rxjava_openserver.bean.OpenServerBean;
import com.qianfeng.day7_retrofit_rxjava_openserver.http.HttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView mExListView;
    private Map<String,List<OpenServerBean.InfoBean>> mData;
    private List<String> mKeys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
    }

    private void initData() {
        HttpUtils.getHttpService()
                .getOpenServerInfo()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                //进行数据转换
                    .map(new Func1<OpenServerBean, Map<String,List<OpenServerBean.InfoBean>>>() {
                        @Override
                        public Map<String, List<OpenServerBean.InfoBean>> call(OpenServerBean openServerBean) {
                            return convert(openServerBean);
                        }
                    })
                    .subscribe(new Action1<Map<String, List<OpenServerBean.InfoBean>>>() {
                        @Override
                        public void call(Map<String, List<OpenServerBean.InfoBean>> map) {
                            mData = map;
                            //获得数据后更新UI
                            mExListView.setAdapter(new MyAdapter());
                        }
                    });

    }

    private Map<String, List<OpenServerBean.InfoBean>> convert(OpenServerBean bean){
        mData = new HashMap<>();
        mKeys = new ArrayList<>();
        for(OpenServerBean.InfoBean infoBean : bean.getInfo()){
            //判断是否存在相同时间
            if(mData.containsKey(infoBean.getAddtime())){
                //如果存在就添加到对应日期的集合中
                List<OpenServerBean.InfoBean> list = mData.get(infoBean.getAddtime());
                list.add(infoBean);
            }else{
                //如果不存在该日期的集合
                List<OpenServerBean.InfoBean> list = new ArrayList<>();
                list.add(infoBean);
                mData.put(infoBean.getAddtime(),list);
                mKeys.add(infoBean.getAddtime());
            }
        }
        return mData;
    }

    private void initViews() {
        mExListView = (ExpandableListView)findViewById(R.id.ex_list_view);
    }

    class MyAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return mKeys.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            String key = mKeys.get(groupPosition);
            return mData.get(key).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return null;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return null;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            TextView textView = new TextView(MainActivity.this);
            textView.setBackgroundColor(Color.RED);
            textView.setText(mKeys.get(groupPosition));
            mExListView.expandGroup(groupPosition);
            return textView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            String key = mKeys.get(groupPosition);
            OpenServerBean.InfoBean infoBean = mData.get(key).get(childPosition);
            TextView textView = new TextView(MainActivity.this);
            textView.setBackgroundColor(Color.YELLOW);
            textView.setText(infoBean.getGname()+"--"+infoBean.getStarttime());
            return textView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }
}
