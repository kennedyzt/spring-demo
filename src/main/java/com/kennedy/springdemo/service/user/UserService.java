package com.kennedy.springdemo.service.user;

import com.kennedy.springdemo.beans.common.PageRequest;
import com.kennedy.springdemo.beans.user.User;
import com.kennedy.springdemo.common.PageModel;

public interface UserService {
    public Integer add(User user) throws Exception;

    public PageModel<User> getListByPage(PageRequest pageRequest);

    public User getUserByName(String username);
}
