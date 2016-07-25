package com.kennedy.springdemo.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kennedy.springdemo.beans.user.User;
import com.kennedy.springdemo.common.ResultMsg;
import com.kennedy.springdemo.service.user.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String toList() {
        return "/user/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd() {
        return "/user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultMsg add(User user) {
        ResultMsg resultMsg = new ResultMsg();
        try {
            userService.add(user);
            resultMsg.setIsSuccess(Boolean.TRUE);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg.setIsSuccess(Boolean.FALSE);
        }
        return resultMsg;
    }

}
