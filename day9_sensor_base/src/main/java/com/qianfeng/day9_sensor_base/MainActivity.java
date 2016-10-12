package com.qianfeng.day9_sensor_base;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

/**
 * 1.获得SensorManager的对象(系统服务)
 * 2.注册对应的感应器
 * 3.实现感应器数据的监听
 */
public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private static final String TAG = "1605";
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        //注册加速度感应器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        //注册光线感应器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_NORMAL);
        //注册压力感应器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE),
                SensorManager.SENSOR_DELAY_NORMAL);
        //注册方向感应器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_NORMAL);
        //注册温度感应器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSensorManager.unregisterListener(this);
    }

    //感应数值改变
    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                showAcc(event);
                break;
            case Sensor.TYPE_LIGHT:
                showLight(event);
                break;
            case Sensor.TYPE_PRESSURE:
                showPressure(event);
                break;
            case Sensor.TYPE_ORIENTATION:
                showOrien(event);
                break;
            case Sensor.TYPE_TEMPERATURE:
                showTemp(event);
                break;
        }
    }

    private void showTemp(SensorEvent event) {
        float x = event.values[0];
        Log.i(TAG, "温度: x:"+x);
    }

    private void showOrien(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        Log.i(TAG, "方向: x:"+x+",y:"+y+",z:"+z);
    }

    private void showAcc(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        Log.i(TAG, "加速度: x:"+x+",y:"+y+",z:"+z);
    }

    private void showLight(SensorEvent event){
        float x = event.values[0];
        Log.i(TAG, "光线: x:"+x);
    }

    private void showPressure(SensorEvent event){
        float x = event.values[0];
        Log.i(TAG, "压力: x:"+x);
    }

    //精度改变
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
