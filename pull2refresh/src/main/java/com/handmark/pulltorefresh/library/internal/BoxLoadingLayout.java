package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.R;

/**
 * 自定义刷新视图
 * Created by xray on 16/10/8.
 */

public class BoxLoadingLayout extends LoadingLayout {

    private AnimationDrawable mAnimDrawable;

    public BoxLoadingLayout(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs, true);
        //设置动画资源
        mHeaderImage.setImageResource(R.drawable.box_anim);
        //给ImageView定义动画
        mAnimDrawable = (AnimationDrawable)mHeaderImage.getDrawable();
        mHeaderText.setVisibility(GONE);
        mHeaderProgress.setVisibility(GONE);
        mSubHeaderText.setVisibility(GONE);
    }

    @Override
    protected int getDefaultDrawableResId() {
        return R.drawable.box_01;
    }

    @Override
    protected void onLoadingDrawableSet(Drawable imageDrawable) {

    }

    @Override
    protected void onPullImpl(float scaleOfLayout) {

    }

    @Override
    protected void pullToRefreshImpl() {

    }

    @Override
    protected void refreshingImpl() {
        if(mAnimDrawable != null)
            mAnimDrawable.start();
    }

    @Override
    protected void releaseToRefreshImpl() {

    }

    @Override
    protected void resetImpl() {
        if(mAnimDrawable != null){
            mAnimDrawable.stop();
        }
        mHeaderImage.clearAnimation();
    }
}
