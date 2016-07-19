package com.kennedy.springdemo.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

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
        return new Class<?>[] { RootConfig.class };
    }

    /**
     * 指定配置类:DispatcherServlet价值的应用上下文,加载Web组件的bean，如控制器，视图解析器，以及处理器映射
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }

    /**
     * 将DispatcherServlet映射到"/"
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    /**
     * 设置对multipart的支持
     */
    // TODO 通过此方式配置multipart的支持未起效
    // @Override
    // protected void customizeRegistration(Dynamic registration) {
    // registration.setMultipartConfig(new
    // MultipartConfigElement("/tmp/springdemo/uploads", 2097152, 4194304, 0));
    // }
}
