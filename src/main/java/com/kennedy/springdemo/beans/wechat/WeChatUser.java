package com.kennedy.springdemo.beans.wechat;

import java.io.Serializable;

/**
 * @Description: 微信用户信息
 * @date: 2016年9月5日 上午9:35:19
 * @author: zengt
 * @version: 1.0
 */
public class WeChatUser extends ErrorCodeModel implements Serializable {

    private static final long serialVersionUID = 1L;
    public String openId; // 对当前公众号的唯一用户标示
    public String nickName; // 用户昵称
    public Integer sex; // 1:男 2：女 0 未知
    public String city; // 用户所在城市
    public String country; // 用户所在国家
    public String headImagUrl; // 用户头像url
    public String groupId; // 用户所在分组ID
    public String privilege; // 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
    public String unionid; // 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadImagUrl() {
        return headImagUrl;
    }

    public void setHeadImagUrl(String headImagUrl) {
        this.headImagUrl = headImagUrl;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

}
