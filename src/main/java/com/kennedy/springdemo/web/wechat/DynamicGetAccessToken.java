package com.kennedy.springdemo.web.wechat;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;

import com.kennedy.springdemo.beans.wechat.Token;
import com.kennedy.springdemo.utils.WeChatUtil;

import net.sf.json.JSONException;

/**
 * @Description: Spring容器初始化完成时获取微信accessToken
 * @date: 2016年9月2日 上午11:51:43
 * @author: zengt
 * @version: 1.0
 */
public class DynamicGetAccessToken implements ApplicationListener<ApplicationEvent> {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    static Boolean flag = true;
    static int num = 0;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (flag) {
            new Thread() {

                @Override
                public void run() {
                    while (true) {
                        try {
                            // 获取accessToken
                            Token token = WeChatUtil.getToken();
                            if (StringUtils.isNotBlank(token.getAccessToken())) {
                                redisTemplate.opsForValue().set("accessToken", token.getAccessToken());
                                num = 0;
                                sleep(60 * 60 * 2 * 1000);
                            }
                            if (num > 10) {
                                Thread.sleep(60 * 60 * 2 * 1000);
                            } else {
                                num++;
                                Thread.sleep(1000 * 30); // 获取的access_token为空
                                                        // 休眠30秒
                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
        flag = false;
    }
}
