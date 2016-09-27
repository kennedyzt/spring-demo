package com.kennedy.springdemo.beans.wechat.message;

import java.io.Serializable;

/**
 * @Description: 微信消息
 * @date: 2016年9月27日 下午4:19:55
 * @author: zengt
 * @version: 1.0
 */
public class Message implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String ToUserName; // 开发者微信号
    private String FromUserName; // 发送方帐号（一个OpenID）
    private Integer CreateTime; // 消息创建时间 （整型）
    private String MsgType; // text
    private String MsgId; // 消息id，64位整型

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

    public Integer getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Integer createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }

}
