package com.kennedy.springdemo.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kennedy.springdemo.beans.common.PageRequest;
import com.kennedy.springdemo.beans.user.User;
import com.kennedy.springdemo.common.PageModel;
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

    @RequestMapping(value = "/getListByPage")
    @ResponseBody
    public PageModel<User> getListByPage(PageRequest pageRequest) {
        PageModel<User> pageModel = null;
        try {
            pageModel = userService.getListByPage(pageRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageModel;
    }

}
