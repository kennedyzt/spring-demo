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

import com.kennedy.springdemo.aop.LogAspect;
import com.kennedy.springdemo.aop.RWAspect;

@Configuration
@ComponentScan(basePackages = { "com.kennedy.springdemo" }, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })
@EnableAspectJAutoProxy // 啟用AspectJ 自動代理
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

    /**
     * @Description: 记录日志
     * @return
     * @author: zengt
     * @date: 2016年8月17日 下午2:59:51
     */
    @Bean
    public LogAspect transactionAdvice() {
        return new LogAspect();
    }

    /**
     * @Description: 读写分离
     * @return
     * @author: zengt
     * @date: 2016年8月17日 下午3:00:07
     */
    @Bean
    public RWAspect rwAspect() {
        return new RWAspect();
    }
}
