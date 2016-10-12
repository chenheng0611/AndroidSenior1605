package com.qianfeng.day9_snake_game;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xray on 16/10/11.
 */

public class SharedPreferencesUtils {

    public static void saveRecord(Context context, int score){
        SharedPreferences.Editor editor = context.getSharedPreferences("snake", Context.MODE_PRIVATE).edit();
        editor.putInt("record",score).commit();
    }

    public static int getRecord(Context context){
        SharedPreferences sp = context.getSharedPreferences("snake",Context.MODE_PRIVATE);
        return sp.getInt("record",0);
    }
}
