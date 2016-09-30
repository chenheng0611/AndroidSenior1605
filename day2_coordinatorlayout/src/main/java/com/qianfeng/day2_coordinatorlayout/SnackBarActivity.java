package com.qianfeng.day2_coordinatorlayout;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SnackBarActivity extends AppCompatActivity {

    private FloatingActionButton mFloatingBtn;
    private CoordinatorLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar);

        initViews();
    }

    private void initViews() {
        mLayout = (CoordinatorLayout)findViewById(R.id.activity_snack_bar);

        mFloatingBtn = (FloatingActionButton)findViewById(R.id.floating_btn);
        mFloatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示SnackBar
                final Snackbar snackbar = Snackbar.make(mLayout, "Hello Snack!!", Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("cancel", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                snackbar.dismiss();
                            }
                        });
                snackbar.show();
            }
        });
    }
}
