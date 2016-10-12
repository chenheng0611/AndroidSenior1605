package com.qianfeng.day8_pack_apk;

import java.io.Serializable;

/**
 * Created by xray on 16/10/10.
 */

public class Student implements Serializable{

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
