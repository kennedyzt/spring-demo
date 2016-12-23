package com.kennedy.springdemo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.kennedy.springdemo.listener.ApplicationListener;

@Configuration
@ComponentScan("com.kennedy.springdemo.web") // 启用注解扫描
@EnableWebMvc // 代替xml配置启用Spring Mvc <mvc: annotation-driven>
@EnableScheduling
public class WebConfig extends WebMvcConfigurerAdapter {
    // JSP视图解析器
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setOrder(2);
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    @Bean
    public ContentNegotiatingViewResolver contentNegotiatingViewResolver() {
        ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
        List<ViewResolver> viewResolvers = new ArrayList<>(); //
        viewResolvers.add(viewResolver());
        viewResolvers.add(freeMarkerViewResolver());
        contentNegotiatingViewResolver.setViewResolvers(viewResolvers);
        return contentNegotiatingViewResolver;
    }

    @Bean
    public FreeMarkerConfig freeMarkerConfig() {
        FreeMarkerConfigurer config = new FreeMarkerConfigurer();
        config.setTemplateLoaderPath("/WEB-INF/views/");
        config.setDefaultEncoding("UTF-8");
        return config;
    }

    public ViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
        freeMarkerViewResolver.setPrefix("");
        freeMarkerViewResolver.setSuffix(".ftl");
        freeMarkerViewResolver.setOrder(1);
        return freeMarkerViewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Scheduled(cron = "55 59 23 * * ?") // 每天23:59:55 执行一次
    public void myTest() {
        System.out.println("访问次数为:" + ApplicationListener.times);
        ApplicationListener.times = 0;
    }

}
