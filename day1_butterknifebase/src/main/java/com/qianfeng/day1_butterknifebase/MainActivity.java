package com.qianfeng.day1_butterknifebase;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindBitmap;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 1.导入包 build.gradle配置
 * 2.在onCreate中,绑定Activity
 * 3.使用@BindView绑定视图,属性不能是私有的
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.text_view)
    TextView mTextView;

    @BindView(R.id.click_btn)
    Button mClickBtn;

    @BindString(R.string.text)
    String mText;

    @BindView(R.id.image_view)
    ImageView mImageView;

    @BindBitmap(R.mipmap.ic_launcher)
    Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mTextView.setText(mText);
        mImageView.setImageBitmap(mBitmap);
    }

    @OnClick(R.id.click_btn)
    void clickButton(){
        Toast.makeText(this, mText, Toast.LENGTH_SHORT).show();
    }
}
