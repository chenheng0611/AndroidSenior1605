package com.qianfeng.day3_searchhistory.db;

import com.qianfeng.day3_searchhistory.bean.SearchHistory;

import java.util.List;

/**
 * 搜索历史数据操作接口
 * Created by xray on 16/9/28.
 */

public interface ISearchHistoryDao {

    void saveSearchHistory(SearchHistory history);
    List<SearchHistory> getSearchHistories(String word);
    void clearSearchHistory();
}
