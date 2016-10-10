package com.kennedy.springdemo.web.wechat;

import com.kennedy.springdemo.beans.wechat.WeChatUser;
import com.kennedy.springdemo.beans.wechat.WebToken;
import com.kennedy.springdemo.utils.WeChatUtil;
import com.kennedy.springdemo.utils.WeChatUtil1;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/wechat")
public class WeChatController {

    @RequestMapping(value = "/getuserinfo", method = RequestMethod.GET)
    public String getUserInfo(@RequestParam("code") String code, @RequestParam("state") String state) {
        try {
            WebToken webToken = WeChatUtil.getWebToken(code);
            WeChatUser user = WeChatUtil.getUserInfo(webToken.getAccessToken(), webToken.getOpenid());
            // 發送給本共總號成員
            WeChatUtil.previewMsg(WeChatUtil.getToken().getAccessToken(), webToken.getOpenid());
            // 发送给其他公众号成员
            WeChatUtil.sendMsg(WeChatUtil1.getToken().getAccessToken());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/wechat/shop";
    }

    @RequestMapping(value = "/message")
    public String receiveMessage(HttpServletRequest request) {
        System.out.println(request.getAttribute("ToUserName"));
        return "";
    }
}
