package com.qianfeng.day3_db_use.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库帮助类
 * Created by xray on 16/9/28.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String CREATE_TABLE = "create table student_tb(_id integer primary key autoincrement,name text,age integer,address text)";
    public static final String DROP_TABLE = "drop table student_tb";
    public static final String DB_NAME = "student.db";
    public static final int DB_VERSION = 1;

    public SQLiteHelper(Context context){
        this(context, DB_NAME, null, DB_VERSION);
    }

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
