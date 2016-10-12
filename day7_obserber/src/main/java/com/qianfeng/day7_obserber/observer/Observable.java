package com.qianfeng.day7_obserber.observer;

/**
 * 被观察者
 * Created by xray on 16/10/9.
 */

public interface Observable {

    /**
     * 注册观察者
     * @param observer
     */
    void addObserver(Observer observer);

    /**
     * 删除观察者
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * 通知所有观察者,状态变化
     */
    void notifyObservers();
}
