package com.qianfeng.day4_jpush_demo;

import android.app.Application;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by xray on 16/9/29.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化JPUsh
        JPushInterface.init(this);
        //开启调试模式
        JPushInterface.setDebugMode(true);
        //设置设备的标签和别名
        Set<String> tags = new HashSet<>();
        tags.add("student");
        tags.add("movie");
        JPushInterface.setAliasAndTags(this,"chen",tags);
    }
}
