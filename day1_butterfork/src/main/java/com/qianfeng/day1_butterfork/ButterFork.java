package com.qianfeng.day1_butterfork;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by xray on 16/9/26.
 */

public class ButterFork {


    public static void bind(final Activity activity){
        //获得类
        Class<? extends Activity> aClass = activity.getClass();
        //获得字段
        Field[] fields = aClass.getDeclaredFields();
        for(Field field : fields){
            //获得注解
            BindView annotation = field.getAnnotation(BindView.class);
            if(annotation != null){
                //获得注解的值
                int id = annotation.value();
                //获得视图
                View view = activity.findViewById(id);
                //把视图赋值给字段
                try {
                    field.set(activity,view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        Method[] methods = aClass.getDeclaredMethods();
        for(final Method method : methods){
            OnClick annotation = method.getAnnotation(OnClick.class);
            if(annotation != null){
                int id = annotation.value();
                View view = activity.findViewById(id);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            method.invoke(activity);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }
}
