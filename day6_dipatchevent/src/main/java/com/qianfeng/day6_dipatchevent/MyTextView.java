package com.qianfeng.day6_dipatchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by xray on 16/10/8.
 */

public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i(MainActivity.TAG, "dispatchTouchEvent: MyTextView--ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(MainActivity.TAG, "dispatchTouchEvent: MyTextView--ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        return true;//代表自己消费该事件
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i(MainActivity.TAG, "onTouchEvent: MyTextView--ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(MainActivity.TAG, "onTouchEvent: MyTextView--ACTION_UP");
                break;
        }
        return super.onTouchEvent(ev);
    }
}
