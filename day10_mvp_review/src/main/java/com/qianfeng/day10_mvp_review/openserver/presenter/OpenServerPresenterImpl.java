package com.qianfeng.day10_mvp_review.openserver.presenter;

import com.qianfeng.day10_mvp_review.bean.OpenServerBean;
import com.qianfeng.day10_mvp_review.openserver.model.OpenServerModel;
import com.qianfeng.day10_mvp_review.openserver.model.OpenServerModelCallback;
import com.qianfeng.day10_mvp_review.openserver.model.OpenServerModelImpl;
import com.qianfeng.day10_mvp_review.openserver.view.OpenServerView;

import java.util.List;
import java.util.Map;

/**
 * 中间层的实现类
 * Created by xray on 16/10/12.
 */

public class OpenServerPresenterImpl implements OpenServerPresenter{

    private OpenServerModel mOpenServerModel;
    private OpenServerView mOpenServerView;

    public OpenServerPresenterImpl(OpenServerView view){
        //把视图传递过来
        mOpenServerView = view;
        //创建模型层对象
        mOpenServerModel = new OpenServerModelImpl();
    }

    @Override
    public void openServer() {
        mOpenServerView.showProgress();
        mOpenServerModel.openServer(new OpenServerModelCallback() {
            @Override
            public void getOpenServerInfo(List<String> keys, Map<String, List<OpenServerBean.InfoBean>> data) {
                mOpenServerView.showOpenServer(keys,data);
                mOpenServerView.dismissProgress();
            }
        });
    }
}
