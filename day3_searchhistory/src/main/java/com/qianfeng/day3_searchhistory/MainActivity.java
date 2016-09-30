package com.qianfeng.day3_searchhistory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.qianfeng.day3_searchhistory.bean.SearchHistory;
import com.qianfeng.day3_searchhistory.db.ISearchHistoryDao;
import com.qianfeng.day3_searchhistory.db.SearchHistoryDaoImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.search_list)
    ListView mSearchList;

    @BindView(R.id.search_view)
    SearchView mSearchView;

    ISearchHistoryDao mSearchDao;

    List<SearchHistory> mHistories;
    private SearchAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initData();

        initViews();
    }

    private void initData() {
        mSearchDao = new SearchHistoryDaoImpl(this);
        mHistories = mSearchDao.getSearchHistories("");
    }

    private void initViews() {
        mAdapter = new SearchAdapter();
        mSearchList.setAdapter(mAdapter);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchHistory history = new SearchHistory(query,0);
                mSearchDao.saveSearchHistory(history);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mHistories = mSearchDao.getSearchHistories(newText);
                mAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    public void onClear(View view) {
        mSearchDao.clearSearchHistory();
        mHistories.clear();
        mAdapter.notifyDataSetChanged();
    }

    class SearchAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mHistories == null ? 0 : mHistories.size();
        }

        @Override
        public Object getItem(int position) {
            return mHistories.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder holder = null;
            if(view == null){
                view = LayoutInflater.from(MainActivity.this).inflate(android.R.layout.simple_list_item_1,parent,false);
                holder = new ViewHolder(view);
            }else{
                holder = (ViewHolder) view.getTag();
            }
            holder.mTextView.setText(mHistories.get(position).getWord());
            return view;
        }

        class ViewHolder{

            @BindView(android.R.id.text1)
            TextView mTextView;

            public ViewHolder(View view){
                ButterKnife.bind(this,view);
                view.setTag(this);
            }

        }
    }
}
