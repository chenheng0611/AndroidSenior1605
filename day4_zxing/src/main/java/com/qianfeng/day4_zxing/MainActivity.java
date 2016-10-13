package com.qianfeng.day4_zxing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.qianfeng.day4_zxing.utils.ZXingUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    Bitmap bitmap;
    private int mSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView)findViewById(R.id.image_view);

        Display d = getWindowManager().getDefaultDisplay();
        mSize = d.getWidth() < d.getHeight() ? d.getWidth() : d.getHeight();
    }

    public void onCreateQRCode(View view) {


        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String path = data.getData().getPath();

        File file = new File(path);
        try {
            InputStream inputStream = new FileInputStream(file);
            Bitmap logo = BitmapFactory.decodeStream(inputStream);
            Log.i("----", "onActivityResult: "+path+","+inputStream);
            bitmap = ZXingUtils.createQRCodeBitmap("客官过来玩玩啊~~~!!Hello~~~",
                    mSize, mSize);
            if(bitmap == null){
                Toast.makeText(this, "生成失败", Toast.LENGTH_SHORT).show();
                return;
            }
//            Bitmap logo = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
            bitmap = ZXingUtils.createBitmapWithLogo(bitmap,logo);
            mImageView.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onParseQRCode(View view) {
        if(bitmap == null){
            Toast.makeText(this, "图片为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String s = ZXingUtils.readContentFromQRCode(bitmap);
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
