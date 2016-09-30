package com.qianfeng.day5_recorder_player;

import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.qianfeng.day5_recorder_player.utils.Constants;

import java.io.File;
import java.io.IOException;

public class RecorderActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private SurfaceView mSurfaceView;
    private SurfaceHolder mHolder;
    private MediaRecorder mRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);

        initViews();
    }

    private void initViews() {
        mSurfaceView = (SurfaceView)findViewById(R.id.surface_view);
        mHolder = mSurfaceView.getHolder();
        mHolder.addCallback(this);
    }

    public void onStopRecord(View view) {
        if(mRecorder != null){
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
            finish();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        initRecorder();
    }

    private void initRecorder() {
        mRecorder = new MediaRecorder();

        mRecorder.reset();
        mRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setPreviewDisplay(mHolder.getSurface());
        mRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        mRecorder.setVideoFrameRate(20);
        mRecorder.setVideoSize(176,144);
        mRecorder.setOutputFile(Constants.FILE_PATH);

//        mRecorder.reset();
//        //设置视频的来源是摄像头
//        mRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
//        //设置视频编码
//        mRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
//        //设置文件保存格式
//        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//        //文件保存路径
//        mRecorder.setOutputFile(Constants.FILE_PATH);
//        //预览
//        mRecorder.setPreviewDisplay(mHolder.getSurface());
//        mRecorder.setVideoFrameRate(20);
//        mRecorder.setVideoSize(176,144);
        //缓存
        try {
            mRecorder.prepare();
            //开始录制
            mRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
