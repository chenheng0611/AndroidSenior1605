package com.qianfeng.day1_butterknife_home.bean;

/**
 * Created by xray on 16/9/26.
 */

public class GiftBean {

    private int imageId;
    private String name;

    public GiftBean() {
    }

    public GiftBean(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
