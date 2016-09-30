package com.kennedy.springdemo.beans.wechat.message;

import java.io.Serializable;

/**
 * @Description: 微信返回消息
 * @date: 2016年9月28日 上午11:02:31
 * @author: zengt
 * @version: 1.0
 */
public class OutputMessage implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String ToUserName;

    private String FromUserName;

    private Long CreateTime;

    private String MsgType = "text";

    private String MediaId;

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

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

}
