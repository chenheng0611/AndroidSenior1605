package com.qianfeng.day9_snake_game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * 1.创建GestureDetector
 * 2.定义OnGestureListener监听
 * 3.在需要手势的组件的onTouchEvent方法中,将事件交给GestureDetector
 */
public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{

    private GestureDetector mGestureDetector;
    //最小的滑动距离
    public static final int MIN_DISTANCE = 50;
    //最小的滑动速度
    public static final int MIN_SPEED = 20;
    private GameView mGameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        Display defaultDisplay = getWindowManager().getDefaultDisplay();

        mGameView = new GameView(this,defaultDisplay.getWidth(),defaultDisplay.getHeight());
        setContentView(mGameView);
        mGameView.start();

        mGestureDetector = new GestureDetector(this,this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将触摸事件交给mGestureDetector处理
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGameView.stop();
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    /**
     * 快速滑动
     * @param e1 开始滑动的事件
     * @param e2 停止滑动的事件
     * @param velocityX 横向的速度
     * @param velocityY 纵向的速度
     * @return
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(e2.getX() - e1.getX() > MIN_DISTANCE &&
                Math.abs(velocityX) > MIN_SPEED){
            mGameView.setDirection(GameView.RIGHT);
//            Toast.makeText(this, "RIGHT", Toast.LENGTH_SHORT).show();
        }else if(e1.getX() - e2.getX() > MIN_DISTANCE &&
                Math.abs(velocityX) > MIN_SPEED){
            mGameView.setDirection(GameView.LEFT);
//            Toast.makeText(this, "LEFT", Toast.LENGTH_SHORT).show();
        }else if(e2.getY() - e1.getY() > MIN_DISTANCE &&
                Math.abs(velocityY) > MIN_SPEED){
            mGameView.setDirection(GameView.DOWN);
//            Toast.makeText(this, "DOWN", Toast.LENGTH_SHORT).show();
        }else if(e1.getY() - e2.getY() > MIN_DISTANCE &&
                Math.abs(velocityY) > MIN_SPEED){
            mGameView.setDirection(GameView.UP);
//            Toast.makeText(this, "UP", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
