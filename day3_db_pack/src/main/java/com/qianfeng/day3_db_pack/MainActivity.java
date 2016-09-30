package com.qianfeng.day3_db_pack;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 1,把db文件放到main/assets下
 * 2,通过getAssets().open方法获得文件流
 * 3,将文件写入到手机磁盘上
 * 4,通过SqliteDatabase.open打开该数据库文件
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "1605";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        readDatabase();
    }
    
    private void readDatabase(){
        SQLiteDatabase database = getDatabase();
        Cursor cursor = database.rawQuery("select * from users", new String[]{});
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            Log.i(TAG, "readDatabase: "+id+","+username+","+password);
        }
        cursor.close();
    }
    
    private SQLiteDatabase getDatabase(){
        try {
            InputStream inputStream = getAssets().open("mydatabase.sqlite");
            String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + File.separator + "db";
            File dir = new File(dirPath);
            if(!dir.exists()){
                dir.mkdir();
            }
            File dbFile = new File(dir,"mydatabase.sqlite");
            OutputStream outputStream = new FileOutputStream(dbFile);
            byte[] buffer = new byte[1024];
            int len = 0;
            while((len = inputStream.read(buffer)) != -1){
                outputStream.write(buffer,0,len);
            }
            outputStream.close();
            inputStream.close();
            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(dbFile,null);
            return database;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
