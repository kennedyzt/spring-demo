package com.kennedy.springdemo.config.schedule;

import com.kennedy.springdemo.listener.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by kennedy on 2016/10/7.
 */
@Configuration
@EnableScheduling
public class PvSchedule {
    @Scheduled(cron = "59 59 23 * * *")   // 每天23:59:59 执行一次
    public void myTest() {
        System.out.println("访问次数为:" + ApplicationListener.times);
        ApplicationListener.times = 0;
    }
}
