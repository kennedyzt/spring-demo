package com.kennedy.springdemo.web.wechat;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kennedy.springdemo.beans.wechat.WeChatUser;
import com.kennedy.springdemo.beans.wechat.WebToken;
import com.kennedy.springdemo.utils.WeChatUtil;

@Controller
@RequestMapping("/wechat")
public class WeChatController {

    @RequestMapping(value = "/getuserinfo", method = RequestMethod.GET)
    public String getUserInfo(@RequestParam("code") String code, @RequestParam("state") String state, Map<String, Object> model) {
        WeChatUser userInfo = null;
        try {
            WebToken webToken = WeChatUtil.getWebToken(code);
            userInfo = WeChatUtil.getUserInfo(webToken.getAccessToken(), webToken.getOpenid());
            // 發送給本共總號成員
            WeChatUtil.previewMsg(WeChatUtil.getToken().getAccessToken(), webToken.getOpenid());
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.put("userInfo", userInfo);
        return "/wechat/shop";
    }

    @RequestMapping(value = "/message")
    public String receiveMessage(HttpServletRequest request) {
        System.out.println(request.getAttribute("ToUserName"));
        return "";
    }
}
