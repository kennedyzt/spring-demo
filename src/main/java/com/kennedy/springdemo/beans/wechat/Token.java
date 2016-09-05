package com.kennedy.springdemo.beans.wechat;

import java.io.Serializable;

/**
 * @Description: AccessToken
 * @date: 2016年9月1日 上午9:40:41
 * @author: zengt
 * @version: 1.0
 */
public class Token extends ErrorCodeModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private String accessToken; // 获取到的凭证
    private Integer expiresIn; // 凭证有效时间，单位：秒

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

}
