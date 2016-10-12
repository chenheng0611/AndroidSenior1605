package com.qianfeng.day10_alipay.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * 数据库工具,用于返回Dao对象
 * Created by xray on 16/10/12.
 */

public class DBUtils {

    public static ProductDao getProductDao(Context context){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "shopping.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster master = new DaoMaster(db);
        DaoSession daoSession = master.newSession();
        return daoSession.getProductDao();
    }
}
