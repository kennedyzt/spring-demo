package com.kennedy.springdemo.beans.wechat.message;

import java.io.Serializable;

/**
 * @Description: 文本消息
 * @date: 2016年12月6日 下午2:25:33
 * @author: zengt
 * @version: 1.0
 */
public class Text implements Serializable {
    private static final long serialVersionUID = -2456803666625116815L;

    private String content;

    public Text() {
        super();
    }

    public Text(String content) {
        super();
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
