package com.kennedy.springdemo.service.menu;

import java.util.List;

import com.kennedy.springdemo.beans.menu.Menu;

public interface MenuService {
    List<Menu> getList() throws Exception;
    public Integer add(Menu menu) throws Exception;
}
