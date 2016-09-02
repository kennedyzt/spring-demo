package com.kennedy.springdemo.web.wechat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/wechat")
public class WeChatController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get() {

        try {
            String accessToken = redisTemplate.opsForValue().get("accessToken");
            System.out.println(accessToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
