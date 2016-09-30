package com.qianfeng.day1_dowload;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "http://gdown.baidu.com/data/wisegame/bb10246b648a16a6/QQ_410.apk";
    public static final int TAG_DOWNLOAD = 100;
    public static final int TAG_FINISH = 200;
    private OkHttpClient mHttpClient;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        mHttpClient = new OkHttpClient();
    }

    private void initViews() {
        mProgressBar = (ProgressBar)findViewById(R.id.download_progress_pb);
    }

    public void onStartDownload(View view) {

        Request request = new Request.Builder()
                .url(URL)
                .get()
                .build();
        mHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        download(response);
                    }
                });
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case TAG_DOWNLOAD:
                    mProgressBar.setProgress(msg.arg1);
                    break;
                case TAG_FINISH:
                    Toast.makeText(MainActivity.this, "finished", Toast.LENGTH_SHORT).show();
                    mProgressBar.setProgress(0);
                    installApk((File)msg.obj);
                    break;
            }
        }
    };

    private void download(Response response){
        //文件的长度
        long length = response.body().contentLength();
        //在SDCard创建文件
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "download_dir";
        File dir = new File(dirPath);
        if(!dir.exists()){
            dir.mkdir();
        }
        File file = new File(dir,System.currentTimeMillis()+".apk");
        try {
            //获得输出流
            OutputStream outputStream = new FileOutputStream(file);
            //获得输入流
            InputStream inputStream = response.body().byteStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            long total = 0; // 下载进度
            //读取数据
            long start = System.currentTimeMillis();
            while((len = inputStream.read(buffer)) != -1){
                //写入数据
                outputStream.write(buffer,0,len);
                total += len;
                int progress = (int)(total * 100.0 / length);
                long end = System.currentTimeMillis();
                if(end - start > 500){
                    //发送进度给UI线程
                    mHandler.obtainMessage(TAG_DOWNLOAD,progress,0)
                            .sendToTarget();
                    start = end;
                }
            }
            outputStream.close();
            inputStream.close();
            //通知UI线程下载完成
            mHandler.obtainMessage(TAG_FINISH,file)
                    .sendToTarget();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void installApk(File file){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file)
            ,"application/vnd.android.package-archive");
        startActivity(intent);
    }
}
