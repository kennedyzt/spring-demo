package com.kennedy.springdemo.beans.wechat.message;

import java.io.Serializable;

/**
 * Created by kennedy on 2016/10/9.
 */
public class Text implements Serializable{
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
