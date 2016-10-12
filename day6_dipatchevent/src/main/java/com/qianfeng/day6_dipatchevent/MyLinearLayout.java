package com.qianfeng.day6_dipatchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by xray on 16/10/8.
 */

public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i(MainActivity.TAG, "dispatchTouchEvent: MyLinearLayout--ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(MainActivity.TAG, "dispatchTouchEvent: MyLinearLayout--ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i(MainActivity.TAG, "onInterceptTouchEvent: MyLinearLayout--ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(MainActivity.TAG, "onInterceptTouchEvent: MyLinearLayout--ACTION_UP");
                break;
        }
        //返回值为false或者默认,不拦截
        //返回值为true表示拦截,不分发给子控件
//        return super.onInterceptTouchEvent(ev);
//        return false;
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i(MainActivity.TAG, "onTouchEvent: MyLinearLayout--ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(MainActivity.TAG, "onTouchEvent: MyLinearLayout--ACTION_UP");
                break;
        }
        return super.onTouchEvent(ev);
    }
}
