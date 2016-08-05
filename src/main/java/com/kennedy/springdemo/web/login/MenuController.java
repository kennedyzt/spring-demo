package com.kennedy.springdemo.web.login;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kennedy.springdemo.beans.menu.Menu;
import com.kennedy.springdemo.service.menu.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    @ResponseBody
    public List<Menu> getList() {
        List<Menu> response = new LinkedList<Menu>();
        try {
            response = menuService.getList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
