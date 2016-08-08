package com.kennedy.springdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description: Spring Security 配置
 * @date: 2016年8月5日 下午4:44:28
 * @author: zengt
 * @version: 1.0
 */

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置Spring Security的Filter链
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
    }

    /**
     * 配置通过拦截器保护请求
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().and().authorizeRequests().antMatchers("/springdemo/**").authenticated().anyRequest().permitAll();
    }

    /**
     * 配置user-detail服务
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("kennedy").password("123456").roles("USER");
    }
}
