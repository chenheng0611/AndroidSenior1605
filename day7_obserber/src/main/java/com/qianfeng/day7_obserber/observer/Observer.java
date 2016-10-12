package com.qianfeng.day7_obserber.observer;

/**
 * 观察者
 * Created by xray on 16/10/9.
 */

public interface Observer {

    /**
     * 进行更新
     */
    void update(String newText);
}
