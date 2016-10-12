package com.qianfeng.day9_move_ball;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener,GestureDetector.OnGestureListener {

    private static final String TAG = "1605";
    private SensorManager mSensorManager;
    private BallView mBallView;
    private GestureDetector gestureDetector;
    public static final int MIN_DISTANCE = 50;
    public static final int MIN_SPEED = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Display d = getWindowManager().getDefaultDisplay();


        mBallView = new BallView(this, d.getWidth(), d.getHeight());
        setContentView(mBallView);
        mBallView.start();

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        mSensorManager.registerListener(this,
//                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
//                SensorManager.SENSOR_DELAY_GAME);

        gestureDetector = new GestureDetector(this, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int x = (int) event.values[0];
        int y = (int) event.values[1];
//        float z = event.values[2];
//        当 x=0 并且 y>0 时，手机顶部的水平位置要大于底部，也就是一般接听电话时手机所处的状态。
//        当 x=0 并且 y<0 时，手机顶部的水平位置要小于底部。手机一般很少处于这种状态。
//        当 y=0 并且 x>0 时，手机右侧的水平位置要大于左侧，也就是右侧被抬起。
//        当 y=0 并且 x<0 时，手机右侧的水平位置要小于左侧，也就是左侧被抬起。
        if(x == 0 && y > 0){
            Log.i(TAG, "onSensorChanged: DOWN");
            mBallView.setDirection(BallView.DOWN);
        }else if(x == 0 && y < 0){
            Log.i(TAG, "onSensorChanged: UP");
            mBallView.setDirection(BallView.UP);
        }else if(y == 0 && x < 0){
            Log.i(TAG, "onSensorChanged: RIGHT");
            mBallView.setDirection(BallView.RIGHT);
        }else if(y == 0 && x > 0){
            Log.i(TAG, "onSensorChanged: LEFT");
            mBallView.setDirection(BallView.LEFT);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

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

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(e2.getX() - e1.getX() > MIN_DISTANCE &&
                Math.abs(velocityX) > MIN_SPEED){
//            Toast.makeText(this, "RIGHT", Toast.LENGTH_SHORT).show();
            mBallView.setDirection(BallView.RIGHT);
        }
        if(e1.getX() - e2.getX() > MIN_DISTANCE &&
                Math.abs(velocityX) > MIN_SPEED){
//            Toast.makeText(this, "LEFT", Toast.LENGTH_SHORT).show();
            mBallView.setDirection(BallView.LEFT);
        }
        if(e2.getY() - e1.getY() > MIN_DISTANCE &&
                Math.abs(velocityY) > MIN_SPEED){
//            Toast.makeText(this, "DOWN", Toast.LENGTH_SHORT).show();
            mBallView.setDirection(BallView.DOWN);
        }
        if(e1.getY() - e2.getY() > MIN_DISTANCE &&
                Math.abs(velocityY) > MIN_SPEED){
//            Toast.makeText(this, "UP", Toast.LENGTH_SHORT).show();
            mBallView.setDirection(BallView.UP);
        }
        return false;
    }
}
