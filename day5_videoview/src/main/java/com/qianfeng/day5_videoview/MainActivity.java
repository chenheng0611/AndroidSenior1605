package com.qianfeng.day5_videoview;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {


    public static final String VIDEO_URL = "http://i.snssdk.com/neihan/video/playback/?video_id=840aebabb21d4ed7a27dfd5f993f86e3&quality=360p&line=1&is_gif=0";
    private VideoView mVideoView;
    private MediaController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mVideoView = (VideoView)findViewById(R.id.video_view);
        //设置视频的路径
        mVideoView.setVideoURI(Uri.parse(VIDEO_URL));
        //播放本地视频
//        mVideoView.setVideoPath("");
        //添加一个媒体控制器
        mController = new MediaController(this);
        mVideoView.setMediaController(mController);
        //开始播放
        mVideoView.start();
    }
}
