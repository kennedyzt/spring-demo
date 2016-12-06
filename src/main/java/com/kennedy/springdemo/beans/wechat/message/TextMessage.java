package com.kennedy.springdemo.beans.wechat.message;

public class TextMessage extends Message {
    private static final long serialVersionUID = 1L;

    private String MsgType = "text";

    private String Content; // 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）

    public TextMessage() {
    }

    public TextMessage(String toUserName, String fromUserName, String content, Long createTime) {
        super.ToUserName = toUserName;
        super.FromUserName = fromUserName;
        super.CreateTime = createTime;
        Content = content;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getMsgType() {
        return MsgType;
    }
}
