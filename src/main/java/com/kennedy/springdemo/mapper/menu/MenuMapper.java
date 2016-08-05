package com.kennedy.springdemo.mapper.menu;

import java.util.List;

import com.kennedy.springdemo.beans.menu.Menu;

public interface MenuMapper {
    public List<Menu> getList();

    public Integer add(Menu menu);
}
