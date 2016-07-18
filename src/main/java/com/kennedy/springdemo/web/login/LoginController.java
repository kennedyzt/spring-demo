package com.kennedy.springdemo.web.login;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kennedy.springdemo.beans.user.User;

@Controller
public class LoginController {
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String toRegister() {
        return "/login/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String toRegister(@Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return "redirect:/register";
        }
        return "redirect:/user/list";
    }

}
