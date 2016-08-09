package com.kennedy.springdemo.config.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.kennedy.springdemo.service.user.UserService;

/**
 * @date: 2016年8月9日 下午2:54:54
 * @author: zengt
 * @version: 1.0
 */
public class SecurityUserDetailsService implements UserDetailsService {
    private UserService userService;

    public SecurityUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.kennedy.springdemo.beans.user.User user = user = userService.getUserByName(username);
        if (null != user) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            List<String> list = userService.getAuthorityByUserId(user.getId());
            for (int i = 0; i < list.size(); i++) {
                authorities.add(new SimpleGrantedAuthority(list.get(i)));
            }
            return new User(username, user.getPassword(), authorities);
        }
        throw new UsernameNotFoundException("用戶名:" + username + "不存在");
    }

}
