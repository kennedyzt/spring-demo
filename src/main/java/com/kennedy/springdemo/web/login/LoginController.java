package com.kennedy.springdemo.web.login;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kennedy.springdemo.beans.user.User;

/**
 * @Description: 用户登录注册模块
 * @date: 2016年7月18日 下午4:12:41
 * @author: zengt
 * @version: 1.0
 */
@Controller
public class LoginController {
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String toRegister() {
        return "/login/register";
    }

    /**
     * @Description: 使用JSR-303对Java校验
     * @param user
     * @param errors
     * @return
     * @author: zengt
     * @date: 2016年7月18日 下午4:12:26
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String toRegister(@RequestParam("img") MultipartFile multipartFile, @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return "redirect:/register";
        }
        return "redirect:/user/list";
    }

}
