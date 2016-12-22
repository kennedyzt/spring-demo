package com.kennedy.springdemo.beans;

/**
 * @Description: 商品
 * @date: 2016年12月22日 上午9:30:37
 * @author: zengt
 * @version: 1.0
 */
public class Goods {
    private String id;
    private String goodsName;
    private Double price;
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

}
