package com.qianfeng.day4_jpush_demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by xray on 16/9/29.
 */

public class MyReceiver extends BroadcastReceiver {

    public static final String TAG = "1605";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Bundle extras = intent.getExtras();
        //判断Action动作
        //收到通知栏
        if(JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(action)){
            String title = extras.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            String alert = extras.getString(JPushInterface.EXTRA_ALERT);
            Log.i(TAG, "onReceive Notification: "+title+","+alert);
            Intent intent1 = new Intent(MainActivity.ACTION_RECEIVE);
            intent1.putExtra("message",alert);
            context.sendBroadcast(intent1);
        }
        //自定义消息
        else if(JPushInterface.ACTION_MESSAGE_RECEIVED.equals(action)){
            String msg = extras.getString(JPushInterface.EXTRA_MESSAGE);
            Log.i(TAG, "onReceive: Message:"+msg);
            Intent intent1 = new Intent(MainActivity.ACTION_RECEIVE);
            intent1.putExtra("message",msg);
            context.sendBroadcast(intent1);
        }
    }
}
