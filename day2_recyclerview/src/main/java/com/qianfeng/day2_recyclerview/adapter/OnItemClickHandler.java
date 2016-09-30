package com.qianfeng.day2_recyclerview.adapter;

/**
 * Created by xray on 16/9/27.
 */

import android.view.View;

/**
 * ITem点击事件回调接口
 */
public interface OnItemClickHandler {
    /**
     * 点击事件
     * @param view
     * @param position
     */
    void itemClick(View view, int position);

    /**
     * 长按事件
     * @param view
     * @param position
     */
    void itemLongClick(View view,int position);
}
