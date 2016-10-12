package com.qianfeng.day10_mvp_review.openserver.model;

import com.qianfeng.day10_mvp_review.bean.OpenServerBean;
import com.qianfeng.day10_mvp_review.http.HttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 开服的实现类
 * Created by xray on 16/10/12.
 */

public class OpenServerModelImpl implements OpenServerModel{


    private ArrayList<String> mKeys;

    @Override
    public void openServer(final OpenServerModelCallback callback) {
        HttpUtils
                .getHttpService()
                .getOpenServerInfo()
                .map(new Func1<OpenServerBean, Map<String,List<OpenServerBean.InfoBean>>>() {
                    @Override
                    public Map<String, List<OpenServerBean.InfoBean>> call(OpenServerBean openServerBean) {
                        return convert(openServerBean);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Action1<Map<String, List<OpenServerBean.InfoBean>>>(){
                    @Override
                    public void call(Map<String, List<OpenServerBean.InfoBean>> map) {
                        if(callback != null){
                            callback.getOpenServerInfo(mKeys,map);
                        }
                    }
                });
    }

    private Map<String, List<OpenServerBean.InfoBean>> convert(OpenServerBean bean){
        Map<String,List<OpenServerBean.InfoBean>> mData = new HashMap<>();
        mKeys = new ArrayList<>();
        for(OpenServerBean.InfoBean infoBean : bean.getInfo()){
            //判断是否存在相同时间
            if(mData.containsKey(infoBean.getAddtime())){
                //如果存在就添加到对应日期的集合中
                List<OpenServerBean.InfoBean> list = mData.get(infoBean.getAddtime());
                list.add(infoBean);
            }else{
                //如果不存在该日期的集合
                List<OpenServerBean.InfoBean> list = new ArrayList<>();
                list.add(infoBean);
                mData.put(infoBean.getAddtime(),list);
                mKeys.add(infoBean.getAddtime());
            }
        }
        return mData;
    }
}
