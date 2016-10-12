package com.qianfeng.day10_mvp_review.openserver.view;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.qianfeng.day10_mvp_review.R;
import com.qianfeng.day10_mvp_review.bean.OpenServerBean;
import com.qianfeng.day10_mvp_review.openserver.presenter.OpenServerPresenterImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 开服的Fragment
 * A simple {@link Fragment} subclass.
 */
public class OpenServerFragment extends Fragment implements OpenServerView{


    private List<String> mKeys = new ArrayList<>();
    private Map<String,List<OpenServerBean.InfoBean>> mData = new HashMap<>();
    private ExpandableListView mExListView;
    private MyAdapter mAdapter;
    private ProgressDialog mDialog;
    private OpenServerPresenterImpl mOpenServerPresenter;

    public OpenServerFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_open_server, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mExListView = (ExpandableListView)view.findViewById(R.id.open_server_list);
        mAdapter = new MyAdapter();
        mExListView.setAdapter(mAdapter);
        //创建Presenter对象
        mOpenServerPresenter = new OpenServerPresenterImpl(this);
        //显示开服数据
        mOpenServerPresenter.openServer();
    }

    @Override
    public void showOpenServer(List<String> keys, Map<String, List<OpenServerBean.InfoBean>> data) {
        mKeys = keys;
        mData = data;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        if(mDialog == null) {
            mDialog = ProgressDialog.show(getActivity(), "", "Loading...");
        }else{
            mDialog.show();
        }
    }

    @Override
    public void dismissProgress() {
        if(mDialog!=null){
            mDialog.dismiss();
        }
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
            TextView textView = new TextView(getActivity());
            textView.setBackgroundColor(Color.RED);
            textView.setText(mKeys.get(groupPosition));
            mExListView.expandGroup(groupPosition);
            return textView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            String key = mKeys.get(groupPosition);
            OpenServerBean.InfoBean infoBean = mData.get(key).get(childPosition);
            TextView textView = new TextView(getActivity());
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
