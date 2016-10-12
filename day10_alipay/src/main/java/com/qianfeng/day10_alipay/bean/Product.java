package com.qianfeng.day10_alipay.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xray on 16/10/12.
 */
@Entity(nameInDb = "tb_shopping_card")
public class Product {

    @Id(autoincrement = true)
    private Long _id;
    private String productName;
    private float productPrice;
    private int productNum;
    private Long productId;
    @Generated(hash = 247472236)
    public Product(Long _id, String productName, float productPrice,
            int productNum, Long productId) {
        this._id = _id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productNum = productNum;
        this.productId = productId;
    }
    @Generated(hash = 1890278724)
    public Product() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public String getProductName() {
        return this.productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public float getProductPrice() {
        return this.productPrice;
    }
    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }
    public int getProductNum() {
        return this.productNum;
    }
    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }
    public Long getProductId() {
        return this.productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
