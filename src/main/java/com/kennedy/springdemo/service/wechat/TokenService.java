package com.kennedy.springdemo.service.wechat;

import org.springframework.stereotype.Service;

import com.kennedy.springdemo.beans.wechat.CheckModel;

/**
 * @Description: 微信开发者验证
 * @date: 2016年8月31日 下午3:09:59
 * @author: zengt
 * @version: 1.0
 */
@Service
public class TokenService {
    public String bbn(String wxToken, CheckModel tokenModel) {
        String signature = tokenModel.getSignature();
        Long timestamp = tokenModel.getTimestamp();
        Long nonce = tokenModel.getNonce();
        String echostr = tokenModel.getEchostr();
        if (signature != null && timestamp != null & nonce != null) {
            return echostr;
        }
        return "error";
    }
}
