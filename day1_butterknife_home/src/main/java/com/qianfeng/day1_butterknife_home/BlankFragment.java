package com.qianfeng.day1_butterknife_home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.qianfeng.day1_butterknife_home.bean.GiftBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    @BindView(R.id.gift_info_list)
    ListView mGiftInfoList;

    List<GiftBean> mGiftList;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        // 绑定视图
        ButterKnife.bind(this,view);

        initData();

        initViews();
        return view;
    }

    private void initViews() {
        mGiftInfoList.setAdapter(new MyAdapter());
    }

    private void initData() {
        mGiftList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            GiftBean bean = new GiftBean(R.mipmap.ic_launcher,mParam1 + i);
            mGiftList.add(bean);
        }
    }

    /**
     * 适配器绑定黄油刀
     */
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mGiftList == null ? 0 : mGiftList.size();
        }

        @Override
        public Object getItem(int position) {
            return mGiftList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder holder = null;
            if(view == null){
                view = LayoutInflater.from(getActivity())
                        .inflate(R.layout.gift_item,parent,false);
                holder = new ViewHolder(view);
            }else{
                holder = (ViewHolder) view.getTag();
            }
            GiftBean giftBean = mGiftList.get(position);
            holder.mGiftImageIv.setImageResource(giftBean.getImageId());
            holder.mGiftNameTv.setText(giftBean.getName());
            return view;
        }

        /**
         * ListView优化
         * 1,ListView宽和高设置为固定的,一般是Match_Parent
         * 2,ConvertView的复用
         * 3,ViewHolder减少findViewById
         * 4,图片压缩,缓存
         * 5,在滑动暂停异步加载
         */
        class ViewHolder{

            @BindView(R.id.gift_image_iv)
            ImageView mGiftImageIv;
            @BindView(R.id.gift_name_tv)
            TextView mGiftNameTv;

            public ViewHolder(View view){
                ButterKnife.bind(this,view);
                view.setTag(this);
            }
        }
    }
}
