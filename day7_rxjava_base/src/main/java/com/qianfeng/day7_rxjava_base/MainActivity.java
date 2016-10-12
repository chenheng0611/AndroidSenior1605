package com.qianfeng.day7_rxjava_base;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "1605";
    public static final String IMAGE_URL = "https://www.baidu.com/img/2016_10_09logo_61d59f1e74db0be41ffe1d31fb8edef3.png";
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView)findViewById(R.id.image_view);
    }

    public void clickBase(View view) {
        //1,定义被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //把新的数据传递给观察者
                subscriber.onNext("Hello! subscriber");
            }
        });
        //2,定义观察者
        Subscriber<String> subscriber = new Subscriber<String>() {
            //观察结束
            @Override
            public void onCompleted() {

            }

            //出现错误
            @Override
            public void onError(Throwable e) {

            }

            //接收被观察者传递的数据
            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext: "+s);
            }
        };
        //3,关联被观察者和观察者
        observable.subscribe(subscriber);
    }

    public void clickThread(View view) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.i(TAG, "call: "+Thread.currentThread().getName());
                subscriber.onNext("Hello Thread");
            }
        })
                //观察者的执行,在主线程进行观察
                .observeOn(AndroidSchedulers.mainThread())
                //被观察者的执行,在子线程进行执行
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, s+" onNext: "+Thread.currentThread().getName());
            }
        });
    }

    public void clickJust(View view) {
        //可以使用just传递简单的数据
        Observable
                .just("Hello Just")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i(TAG, "just call: "+s);
                    }
                });
    }

    public void clickMap(View view) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("1234");
            }
        })
                //map方法的作用是将数据转换成其他类型或格式
                .map(new Func1<String, Integer>() {

                    @Override
                    public Integer call(String s) {
                        return Integer.parseInt(s);
                    }
                })
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer * 1000;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.i(TAG, "call: use map --- " +integer);
                    }
                });
    }

    public void clickImage(View view) {
        //使用RXjava进行异步操作,加载网络图片
        Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                try {
                    URL url = new URL(IMAGE_URL);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = conn.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();
                    subscriber.onNext(bitmap);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        })
                .observeOn(AndroidSchedulers.mainThread())//观察者负责UI的更新
                .subscribeOn(Schedulers.io())               //被观察者负责异步操作
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        mImageView.setImageBitmap(bitmap);
                    }
                });
    }
}
