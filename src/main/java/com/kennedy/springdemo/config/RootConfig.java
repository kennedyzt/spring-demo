package com.kennedy.springdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.kennedy.springdemo.aop.Audience;

@Configuration
@ComponentScan(basePackages = { "com.kennedy.springdemo" }, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })
@EnableAspectJAutoProxy //  啟用AspectJ 自動代理
public class RootConfig {
    /**
     * @Description: 配置multipart的支持
     * @return
     * @throws Exception
     * @author: zengt
     * @date: 2016年7月19日 下午5:31:42
     */
    @Bean
    public MultipartResolver multipartResolver() throws Exception {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setUploadTempDir(new FileSystemResource("/tmp/springdemo/uploads"));
        commonsMultipartResolver.setMaxUploadSize(2097152);
        commonsMultipartResolver.setMaxUploadSizePerFile(2097152);
        return commonsMultipartResolver;
    }

    @Bean
    public Audience audience() {
        return new Audience();
    }
}
