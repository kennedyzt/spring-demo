package com.kennedy.springdemo.beans.wechat.menu;

public class ClickButton extends Button {
    private String type;
    private String key;

    public ClickButton() {
        super();
    }

    public ClickButton(String name, String type, String key) {
        super();
        super.name = name;
        this.type = type;
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
