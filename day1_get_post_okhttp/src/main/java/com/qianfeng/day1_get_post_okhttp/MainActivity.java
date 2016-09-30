package com.qianfeng.day1_get_post_okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "http://192.168.57.129:8080/myWeb/MyServlet";
    private static final String TAG = "1605";
    private OkHttpClient mHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHttpClient = new OkHttpClient();
    }

    public void onGetClick(View view) {
        //异步请求GET
        Request request = new Request.Builder()
                .url(URL + "?name=chenheng")
                .get()
                .build();
        mHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String s = response.body().string();
                        Log.i(TAG, "onResponse: "+s);
                    }
                });
    }

    public void onPostClick(View view) {
        //创建请求体,添加参数
        FormBody body = new FormBody.Builder()
                .add("name", "android1605")
                .build();
        //异步Post请求
        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();
        mHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String s = response.body().string();
                        Log.i(TAG, "onResponse: "+s);
                    }
                });
    }
}
