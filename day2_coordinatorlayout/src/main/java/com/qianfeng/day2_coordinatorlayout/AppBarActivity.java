package com.qianfeng.day2_coordinatorlayout;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AppBarActivity extends AppCompatActivity {

    private AppBarLayout mAppBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar);

        initViews();
    }

    private void initViews() {
        mAppBarLayout = (AppBarLayout)findViewById(R.id.appbar_layout);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                setTitle("offset:"+verticalOffset);
                float alpha = 1.0f + verticalOffset * 1.0f / 320;
                mAppBarLayout.setAlpha(alpha);
            }
        });
    }
}
