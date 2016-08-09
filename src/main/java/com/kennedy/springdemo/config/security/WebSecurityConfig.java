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
        http.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().authenticated().and().logout().logoutUrl("/logout").logoutSuccessUrl("/user/login").and().formLogin()
            .loginPage("/login").permitAll().and().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").and().authorizeRequests().antMatchers("/member/**")
            .access("hasRole('ADMIN') and hasRole('MENBER')").anyRequest().authenticated().and().httpBasic().and().csrf().disable();
    }
}
