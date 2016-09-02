package com.kennedy.springdemo.beans.wechat;

import java.io.Serializable;

/**
 * @Description: 微信错误返回码
 * @date: 2016年8月31日 下午3:02:36
 * @author: zengt
 * @version: 1.0
 */
public class ErrorCodeModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private String errcode; // 全局返回码
    private String errmsg; // 说明

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
