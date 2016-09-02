package com.kennedy.springdemo.config;

import java.net.ConnectException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;

import com.kennedy.springdemo.beans.wechat.AccessToken;
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

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (flag) {
            new Thread() {

                @Override
                public void run() {
                    while (true) {
                        try {
                            // 获取accessToken
                            AccessToken accessToken = WeChatUtil.getAccessToken();
                            if (accessToken != null) {
                                redisTemplate.opsForValue().set("accessToken", accessToken.getAccessToken());
                                sleep(60 * 60 * 2 * 1000);
                            } else {
                                Thread.sleep(1000 * 3); // 获取的access_token为空
                                                        // 休眠3秒
                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (ConnectException e) {
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
