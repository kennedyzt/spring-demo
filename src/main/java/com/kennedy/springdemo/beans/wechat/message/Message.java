package com.kennedy.springdemo.beans.wechat.message;

import java.io.Serializable;

/**
 * @Description: 微信返回消息
 * @date: 2016年9月28日 上午11:02:31
 * @author: zengt
 * @version: 1.0
 */
public class Message implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String ToUserName;

    public String FromUserName;

    public Long CreateTime;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }
}
