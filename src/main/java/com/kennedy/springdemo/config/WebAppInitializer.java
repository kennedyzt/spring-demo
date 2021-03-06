package com.kennedy.springdemo.config;

import com.kennedy.springdemo.config.datasources.DataConfig;
import com.kennedy.springdemo.config.security.WebSecurityConfig;
import com.kennedy.springdemo.config.websocket.WebSocketConfig;
import com.kennedy.springdemo.listener.ApplicationListener;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


/**
 * @Description:spring容器初始化，项目部署在Servlet3.0容器中的时候，容器会自动发现此文件
 * @date: 2016年7月18日 上午11:40:48
 * @author: zengt
 * @version: 1.0
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * ContextLoaderListener需要加载应用中其它的bean，这些bean通常是驱动应用后端的中间层和数据层组件
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class, DataConfig.class, WebSecurityConfig.class, ApplicationListener.class};
    }

    /**
     * 指定配置类:DispatcherServlet价值的应用上下文,加载Web组件的bean，如控制器，视图解析器，以及处理器映射
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class, WebSocketConfig.class};
    }

    /**
     * 将DispatcherServlet映射到"/"
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
