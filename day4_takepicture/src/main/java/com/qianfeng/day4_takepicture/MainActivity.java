package com.qianfeng.day4_takepicture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private SurfaceView mSurfaceView;
    private SurfaceHolder mHolder;
    private Camera mCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {

        mSurfaceView = (SurfaceView)findViewById(R.id.surface_view);
        //获得Holder,持有者
        mHolder = mSurfaceView.getHolder();
        //添加回调
        mHolder.addCallback(this);
    }

    public void onTakePicture(View view) {
        //拍照处理
        mCamera.takePicture(null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {

                //自动对焦
                mCamera.autoFocus(null);

                //保存图片
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                        + File.separator + "pic";

                File dir = new File(dirPath);
                if(!dir.exists()){
                    dir.mkdir();
                }

                File file = new File(dir,System.currentTimeMillis() + ".jpg");
                try {
                    OutputStream output = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,output);
                    output.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //重新预览
                mCamera.stopPreview();
                mCamera.startPreview();
            }
        });
    }

    //创建好后调用
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //初始化摄像头
        initCamera();
    }

    private void initCamera() {
        //打开摄像头
        mCamera = Camera.open();
        //给摄像头设置预览
        try {
            mCamera.setPreviewDisplay(mHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //开始预览
        mCamera.startPreview();
    }

    //尺寸变化时调用
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    //销毁时
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(mCamera != null){
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }
}
