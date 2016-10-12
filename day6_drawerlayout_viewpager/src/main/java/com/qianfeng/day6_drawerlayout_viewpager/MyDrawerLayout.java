package com.qianfeng.day6_drawerlayout_viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by xray on 16/10/8.
 */

public class MyDrawerLayout extends DrawerLayout {

    private ViewPager mViewPager;

    public MyDrawerLayout(Context context) {
        this(context,null);
    }

    public MyDrawerLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews();
    }

    private void initViews() {
        mViewPager = (ViewPager)findViewById(R.id.view_pager);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //内部拦截法
//        mViewPager = (ViewPager)findViewById(R.id.view_pager);
//        if(mViewPager.getCurrentItem() != 0){
//            return false;
//        }
        return super.onInterceptTouchEvent(ev);
    }
}
