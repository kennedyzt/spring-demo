package com.kennedy.springdemo.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
/**
 * 
 * 
 * @Description: 如果希望借助AbstractSecurityWebApplicationInitializer以java方式配置Delegating-FilterProxy的话
 * 需要创建此扩展类，AbstractSecurityWebApplicationInitializer实现了WebApplication-Initializer，因此Spring会发现它，并用它在
 * Web容器中注册DelegatingFilterProxy
 * @date: 2016年8月9日 上午11:15:19
 * @author: zengt
 * @version: 1.0
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
    
}
