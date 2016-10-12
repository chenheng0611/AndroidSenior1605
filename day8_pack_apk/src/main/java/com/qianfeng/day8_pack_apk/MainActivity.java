package com.qianfeng.day8_pack_apk;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //读取包信息
        try {
            ApplicationInfo appInfo = getPackageManager().getApplicationInfo(
                    getPackageName(),
                    PackageManager.GET_META_DATA);
            String channel = appInfo.metaData.getString("CHANNEL");
            switch (channel){
                case "xiaomi":
                    setTitle("欢迎使用小米市场下载App");
                    break;
                case "huawei":
                    setTitle("华为市场欢迎你");
                    break;
                case "_360":
                    setTitle("360给你360度的用户体验");
                    break;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Student stu = new Student();
        stu.setName("zhangsan");
        Teacher teacher = new Teacher();
        teacher.setName("lisi");
    }
}
