package com.kennedy.springdemo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.kennedy.springdemo.service.user.UserService;

/**
 * @Description: Spring Security 配置
 * @date: 2016年8月5日 下午4:44:28
 * @author: zengt
 * @version: 1.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // 开发环境提供内置用户
        // auth.inMemoryAuthentication().withUser("zengt").password("123456").roles("USER");
        auth.userDetailsService(new SecurityUserDetailsService(userService));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 解决不能加载iframe 页面报一个"Refused to display 'http://......' in a frame
        // because it set 'X-Frame-Options' to 'DENY'. "错误
        http.headers().frameOptions().disable();
        // 对静态资源不拦截
        http.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().authenticated();
        // 配置登录，退出页面
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/user/login").and().formLogin().loginPage("/login").permitAll();
        // 权限控制
        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/member/**").access("hasRole('ADMIN') and hasRole('MENBER')");
        // 禁用CSRF
        http.csrf().disable();
    }
}
