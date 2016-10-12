package com.qianfeng.day7_obserber.views;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import com.qianfeng.day7_obserber.observer.Observable;
import com.qianfeng.day7_obserber.observer.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * EditText 被观察者
 * Created by xray on 16/10/9.
 */

public class MyEditText extends EditText implements Observable {

    //观察者集合
    private List<Observer> mObservers = new ArrayList<>();

    public MyEditText(Context context) {
        this(context,null);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        //添加文字修改的监听
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                notifyObservers();
            }
        });
    }

    @Override
    public void addObserver(Observer observer) {
        mObservers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        mObservers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer : mObservers){
            observer.update(getText().toString());
        }
    }
}
