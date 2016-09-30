package com.qianfeng.day2_recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.day2_recyclerview.R;

import java.util.List;
import java.util.Random;

/**
 * Created by xray on 16/9/27.
 */

public class HeaderMyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final int TYPE_ITEM = 1;
    public static final int TYPE_HEADER = 2;
    private Context mContext;
    private List<String> mData;

    public HeaderMyAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    //通过位置获得视图的类型
    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    //创建视图,返回viewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == TYPE_HEADER){
            View header = LayoutInflater.from(mContext).inflate(R.layout.item_header,parent,false);
            return new HeaderViewHolder(header);
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(view);
    }

    //更新视图
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(position != 0){
            MyViewHolder vh = (MyViewHolder)holder;
            String s = mData.get(position + 1);
            int height = new Random().nextInt(200) + 200;
            vh.mTextView.setLayoutParams(
                    new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            height));
            vh.mTextView.setText(s);
        }else{
            HeaderViewHolder vh = (HeaderViewHolder)holder;
            vh.mImageView.setImageResource(R.mipmap.ic_launcher);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;

        public HeaderViewHolder(View view){
            super(view);
            mImageView = (ImageView)view.findViewById(R.id.header_iv);
        }
    }


}
