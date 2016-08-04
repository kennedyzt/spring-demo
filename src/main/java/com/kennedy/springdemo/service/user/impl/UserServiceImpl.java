package com.kennedy.springdemo.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kennedy.springdemo.beans.user.User;
import com.kennedy.springdemo.mapper.user.UserMapper;
import com.kennedy.springdemo.service.user.UserService;

/**
 * @Description: TODO
 * @date: 2016年7月22日 下午3:51:56
 * @author: zengt
 * @version: 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public Integer add(User user) throws Exception {
        return userMapper.add(user);
    }

}
