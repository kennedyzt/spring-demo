package com.kennedy.springdemo.service.menu.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kennedy.springdemo.beans.menu.Menu;
import com.kennedy.springdemo.mapper.menu.MenuMapper;
import com.kennedy.springdemo.service.menu.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;

    @Override
    public Integer add(Menu menu) throws Exception {
        return menuMapper.add(menu);
    }

    @Override
    public List<Menu> getList() throws Exception {
        return menuMapper.getList();
    }

}
