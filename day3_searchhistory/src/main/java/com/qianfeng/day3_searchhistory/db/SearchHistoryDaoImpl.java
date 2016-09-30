package com.qianfeng.day3_searchhistory.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.qianfeng.day3_searchhistory.bean.SearchHistory;

import java.util.List;

/**
 * 搜索历史接口的实现类
 * Created by xray on 16/9/28.
 */

public class SearchHistoryDaoImpl implements ISearchHistoryDao{


    public static final String DB_NAME = "search.db";
    private SearchHistoryDao mDao;

    public SearchHistoryDaoImpl(Context context){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster master = new DaoMaster(db);
        DaoSession daoSession = master.newSession();
        mDao = daoSession.getSearchHistoryDao();
    }

    @Override
    public void saveSearchHistory(SearchHistory history) {
        //先看有没有该文字存在
        List<SearchHistory> list = mDao.queryBuilder().where(
                SearchHistoryDao.Properties.Word.eq(history.getWord()))
                .build()
                .list();
        if(list.size() == 0){
            //插入该记录
            mDao.insert(history);
        }else{
            //搜索次数加1
            SearchHistory history1 = list.get(0);
            history1.setCount(history1.getCount() + 1);
            mDao.update(history1);
        }
    }

    @Override
    public List<SearchHistory> getSearchHistories(String word) {
        //模糊查询
        List<SearchHistory> list = mDao.queryBuilder().where(
                SearchHistoryDao.Properties.Word.like(word + "%"))
                .orderDesc(SearchHistoryDao.Properties.Count)
                .build().list();
        return list;
    }

    @Override
    public void clearSearchHistory() {
        mDao.deleteAll();
    }
}
