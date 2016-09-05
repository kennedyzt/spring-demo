package com.kennedy.springdemo.beans.wechat.menu;

/**
 * @Description: TODO
 * @date: 2016年9月5日 下午3:38:02
 * @author: zengt
 * @version: 1.0
 */
public enum MenuType {
    CLICK("click"), VIEW("view");

    private String type;

    MenuType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
