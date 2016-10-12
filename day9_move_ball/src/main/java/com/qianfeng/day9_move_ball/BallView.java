package com.qianfeng.day9_move_ball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

/**
 * 小球视图
 * Created by xray on 16/10/11.
 */

public class BallView extends View {

    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;
    private int mDirection = UP; //方向
    private Point mPoint;   //坐标
    private Paint mPaint;   //画笔
    private boolean isRunning = false;
    private int mWidth;
    private int mHeight;

    public BallView(Context context,int mWidth,int mHeight) {
        super(context);
        this.mWidth = mWidth;
        this.mHeight = mHeight;
    }

    public BallView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setDirection(int mDirection) {
        this.mDirection = mDirection;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画小球
        canvas.drawCircle(mPoint.x,mPoint.y,20,mPaint);
    }

    /**
     * 初始化小球
     */
    private void initBall(){
        mPoint = new Point(50,50);
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        isRunning = true;
    }

    /**
     * 移动球
     */
    private void move(){
        switch (mDirection){
            case UP:
                if(mPoint.y > 10) {
                    mPoint.y -= 10;
                }
                break;
            case DOWN:
                if(mPoint.y < mHeight - 10){
                    mPoint.y += 10;
                }
                break;
            case LEFT:
                if(mPoint.x > 10) {
                    mPoint.x -= 10;
                }
                break;
            case RIGHT:
                if(mPoint.x < mWidth - 10) {
                    mPoint.x += 10;
                }
                break;
        }
    }

    /**
     * 启动小球
     */
    public void start(){
        initBall();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(isRunning){
                    move();
                    postInvalidate();// 刷新界面
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
