package com.kennedy.springdemo.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kennedy.springdemo.beans.common.PageRequest;
import com.kennedy.springdemo.beans.user.User;
import com.kennedy.springdemo.common.PageModel;
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

    @Override
    public PageModel<User> getListByPage(PageRequest pageRequest) {
        List<User> list = userMapper.getListByPage(pageRequest);
        return new PageModel<>(pageRequest.getTotalCount(), list);
    }

    @Override
    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    public List<String> getAuthorityByUserId(Integer userId) {
        return userMapper.getAuthorityByUserId(userId);
    }

}
