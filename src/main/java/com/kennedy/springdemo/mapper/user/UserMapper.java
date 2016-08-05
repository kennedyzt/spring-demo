package com.kennedy.springdemo.mapper.user;

import java.util.List;

import com.kennedy.springdemo.beans.common.PageRequest;
import com.kennedy.springdemo.beans.user.User;

public interface UserMapper {
    public Integer add(User user);

    public List<User> getListByPage(PageRequest pageRequest);
}
