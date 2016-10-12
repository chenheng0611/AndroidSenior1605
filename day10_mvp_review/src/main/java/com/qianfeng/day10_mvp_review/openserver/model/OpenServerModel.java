package com.qianfeng.day10_mvp_review.openserver.model;

/**
 * 开服接口
 * Created by xray on 16/10/12.
 */

public interface OpenServerModel {

    /**
     * 进行开服
     * @param callback
     */
    void openServer(OpenServerModelCallback callback);
}
