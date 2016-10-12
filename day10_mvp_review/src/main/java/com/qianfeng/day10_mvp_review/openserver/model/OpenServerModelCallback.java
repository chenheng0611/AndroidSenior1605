package com.qianfeng.day10_mvp_review.openserver.model;

import com.qianfeng.day10_mvp_review.bean.OpenServerBean;

import java.util.List;
import java.util.Map;

/**
 * 开服数据回调接口
 * Created by xray on 16/10/12.
 */

public interface OpenServerModelCallback {

    /**
     * 获得开服所需的数据
     * @param keys
     * @param data
     */
    void getOpenServerInfo(List<String> keys, Map<String,List<OpenServerBean.InfoBean>> data);

}
