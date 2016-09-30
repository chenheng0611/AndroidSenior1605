package com.qianfeng.day2_coordinatorlayout;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义行为
 * 继承CoordinatorLayout.Behavior
 * 实现layoutDependsOn 实现依赖关系
 * Created by xray on 16/9/27.
 */

public class RotateBehavior extends CoordinatorLayout.Behavior {

    private float mOrignY = 0.0f;

    public RotateBehavior() {
        super();
    }

    public RotateBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        //根据依赖视图的变化更新当前视图
        float y = dependency.getY();
        child.setRotation((mOrignY - y) * 5);
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        if(mOrignY == 0){
            mOrignY = dependency.getY();
        }
        //定义依赖关系
        return dependency instanceof NestedScrollView &&
                dependency.getId() == R.id.scroll_view;
    }
}
