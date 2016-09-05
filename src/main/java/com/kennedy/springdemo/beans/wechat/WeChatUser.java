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
    public Boolean subscribe; // 用户是否订阅该公众号
    public String openId; // 对当前公众号的唯一用户标示
    public String nickName; // 用户昵称
    public Integer sex; // 1:男 2：女 0 未知
    public String city; // 用户所在城市
    public String country; // 用户所在国家
    public String province; // 用户所在省份
    public String language; // 用户语言
    public String headImagUrl; // 用户头像url
    public String subscribeTime; // 用户关注时间戳
    public String unionId; // 只有用户将公众号绑定到微信开放平台账号后才会出现该字段
    public String remark; // 公众号运营者对粉丝的备注
    public String groupId; // 用户所在分组ID

    public Boolean getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHeadImagUrl() {
        return headImagUrl;
    }

    public void setHeadImagUrl(String headImagUrl) {
        this.headImagUrl = headImagUrl;
    }

    public String getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(String subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

}
