package com.kennedy.springdemo.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.kennedy.springdemo.service.user.UserService;

/**
 * @Description: 网站访问量监听器
 * @date: 2016年9月29日 上午10:24:16
 * @author: zengt
 * @version: 1.0
 */
@WebListener
public class ApplicationListener implements HttpSessionListener {
    public static Integer times = 0;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        times++;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = "";
        if (request.getHeader("x-forwarded-for") == null) {
            ip = request.getRemoteAddr();
        }
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(httpSessionEvent.getSession().getServletContext());
        UserService pvService = (UserService) context.getBean("userServiceImpl");
        pvService.getUserByName("1");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println(1);
    }

}
