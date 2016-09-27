package com.kennedy.springdemo.service.wechat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

/**
 * @Description: 微信开发者验证
 * @date: 2016年8月31日 下午3:09:59
 * @author: zengt
 * @version: 1.0
 */
@Service
public class TokenService {
    public String validate(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        // String echostr = request.getParameter("echostr");
        if (signature != null && timestamp != null & nonce != null) {
            return signature;
        }
        return "error";
    }
}
