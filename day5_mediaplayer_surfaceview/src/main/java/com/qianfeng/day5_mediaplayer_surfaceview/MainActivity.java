package com.qianfeng.day5_mediaplayer_surfaceview;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    public static final String VIDEO_URL = "http://i.snssdk.com/neihan/video/playback/?video_id=840aebabb21d4ed7a27dfd5f993f86e3&quality=360p&line=1&is_gif=0";

    private SurfaceView mSurfaceView;
    private SurfaceHolder mHolder;
    private MediaPlayer mMediaPlayer;
    private boolean isStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mSurfaceView = (SurfaceView)findViewById(R.id.surface_view);
        //获得Surface的持有者
        mHolder = mSurfaceView.getHolder();
        //添加回调接口
        mHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        initMediaPlayer();
    }

    private void initMediaPlayer() {
        mMediaPlayer = new MediaPlayer();
        //重置播放器
        mMediaPlayer.reset();
        //设置播放资源
        try {
            mMediaPlayer.setDataSource(this, Uri.parse(VIDEO_URL));
            //设置播放的显示
            mMediaPlayer.setDisplay(mHolder);
            //异步缓冲
            mMediaPlayer.prepareAsync();
            //添加缓冲监听
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    isStop = false;
                    //开始播放
                    mMediaPlayer.start();
                }
            });
            //....
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(mMediaPlayer != null){
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void onStartPlay(View view) {
        mMediaPlayer.start();
        if(isStop){
            //重置播放器
            mMediaPlayer.reset();
            try {
                mMediaPlayer.setDataSource(this, Uri.parse(VIDEO_URL));
            } catch (IOException e) {
                e.printStackTrace();
            }
            mMediaPlayer.prepareAsync();
        }
    }

    public void onPausePlay(View view) {
        mMediaPlayer.pause();
    }

    public void onStopPlay(View view) {
        isStop = true;
        mMediaPlayer.stop();
    }
}
