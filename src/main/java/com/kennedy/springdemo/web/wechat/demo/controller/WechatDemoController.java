package com.kennedy.springdemo.web.wechat.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kennedy.springdemo.utils.WeChatUtil;

@Controller
@RequestMapping("/wechat")
public class WechatDemoController {
    @RequestMapping("/addkf")
    public String addKf() {
        WeChatUtil.addKF();
        return "/wechat/demo/addKf";
    }
}
