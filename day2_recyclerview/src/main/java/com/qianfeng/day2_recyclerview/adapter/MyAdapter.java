package com.qianfeng.day2_recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.qianfeng.day2_recyclerview.LayoutManagerActivity;
import com.qianfeng.day2_recyclerview.R;

import java.util.List;
import java.util.Random;

/**
 * Created by xray on 16/9/27.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private Context mContext;
    private List<String> mData;
    //监听器接口
    private OnItemClickHandler onItemClickHandler;

    //设置监听器
    public void setOnItemClickHandler(OnItemClickHandler handler){
        onItemClickHandler = handler;
    }

    public MyAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    //创建视图,返回viewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(view);
    }

    //更新视图
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        String s = mData.get(position);
        int height = new Random().nextInt(200) + 200;
        holder.mTextView.setLayoutParams(
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        height));
        holder.mTextView.setText(s);
        //绑定视图的事件到回调接口上
        if(onItemClickHandler != null){
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickHandler.itemClick(v,position);
                }
            });
            holder.mTextView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickHandler.itemLongClick(v,position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }
}