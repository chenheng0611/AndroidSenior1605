package com.qianfeng.day1_retrofit_gson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.qianfeng.day1_retrofit_gson.bean.GiftListBean;
import com.qianfeng.day1_retrofit_gson.http.GiftListService;
import com.qianfeng.day1_retrofit_gson.http.HttpUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "1605";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onTestClick(View view) {
        GiftListService service = HttpUtils.getGiftListService();
        service.getGiftList(1)
                .enqueue(new Callback<GiftListBean>() {
                    @Override
                    public void onResponse(Call<GiftListBean> call, Response<GiftListBean> response) {
                        GiftListBean bean = response.body();
                        showGiftList(bean);
                    }

                    @Override
                    public void onFailure(Call<GiftListBean> call, Throwable t) {

                    }
                });
    }

    private void showGiftList(GiftListBean bean){
        for(GiftListBean.ListBean listBean :bean.getList()){
            Log.i(TAG, "showGiftList: "+listBean.getGname());
        }
    }
}
