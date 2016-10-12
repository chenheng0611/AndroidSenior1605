package com.qianfeng.day10_mvp_review.openserver.view;

import com.qianfeng.day10_mvp_review.bean.OpenServerBean;

import java.util.List;
import java.util.Map;

/**
 * 开服视图接口
 * Created by xray on 16/10/12.
 */

public interface OpenServerView {

    /**
     * 显示开服的列表
     * @param keys
     * @param data
     */
    void showOpenServer(List<String> keys, Map<String,List<OpenServerBean.InfoBean>> data);

    /**
     * 显示进度对话框
     */
    void showProgress();
    void dismissProgress();
}
