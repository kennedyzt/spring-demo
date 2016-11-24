package com.kennedy.springdemo.beans.wechat.menu;

/**
 * @Description: 跳转页面的按钮
 * @date: 2016年9月26日 上午9:29:58
 * @author: zengt
 * @version: 1.0
 */
public class ViewButton extends Button {
    private String type;
    private String url;

    public ViewButton() {
        super();
    }

    public ViewButton(String name, String type, String url) {
        super.name = name;
        this.type = type;
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
