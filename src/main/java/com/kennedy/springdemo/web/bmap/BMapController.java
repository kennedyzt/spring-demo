package com.kennedy.springdemo.web.bmap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description: 百度地图
 * @date: 2016年8月18日 下午3:56:50
 * @author: zengt
 * @version: 1.0
 */
@Controller
public class BMapController {
    @RequestMapping(value = "/bmap", method = RequestMethod.GET)
    public String toRegister() {
        return "/demo/bmap";
    }
}
