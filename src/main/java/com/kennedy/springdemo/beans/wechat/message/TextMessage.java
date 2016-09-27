package com.kennedy.springdemo.beans.wechat.message;

/**
 * @Description: 文本消息
 * @date: 2016年9月27日 下午4:24:25
 * @author: zengt
 * @version: 1.0
 */
public class TextMessage extends Message {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String Content; // 文本消息内容

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

}
