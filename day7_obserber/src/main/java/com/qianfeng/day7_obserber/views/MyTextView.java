package com.qianfeng.day7_obserber.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.qianfeng.day7_obserber.observer.Observer;

/**
 * 观察者
 * Created by xray on 16/10/9.
 */

public class MyTextView extends TextView implements Observer{
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
    public void update(String newText) {
        setText(newText);
    }
}
