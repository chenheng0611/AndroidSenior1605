package com.qianfeng.day4_zxing;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.qianfeng.day4_zxing.utils.ZXingUtils;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView)findViewById(R.id.image_view);
    }

    public void onCreateQRCode(View view) {
        bitmap = ZXingUtils.createQRCodeBitmap("客官过来玩玩啊~~~!!Hello~~~",
                400, 400);
        if(bitmap == null){
            Toast.makeText(this, "生成失败", Toast.LENGTH_SHORT).show();
            return;
        }
        mImageView.setImageBitmap(bitmap);
    }


    public void onParseQRCode(View view) {
        if(bitmap == null){
            Toast.makeText(this, "图片为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String s = ZXingUtils.readContentFromQRCode(bitmap, 400, 400);
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
