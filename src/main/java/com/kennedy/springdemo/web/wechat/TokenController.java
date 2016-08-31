package com.kennedy.springdemo.web.wechat;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kennedy.springdemo.beans.wechat.CheckModel;
import com.kennedy.springdemo.service.wechat.TokenService;

/**
 * @Description: 验证微信URL
 * @date: 2016年8月31日 下午3:05:02
 * @author: zengt
 * @version: 1.0
 */
@Controller
@RequestMapping("/wechat")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    /**
     * 开发者模式token校验
     * @param wxAccount 开发者url后缀
     * @param response
     * @param tokenModel
     * @throws ParseException
     * @throws IOException
     */
    @RequestMapping(value = "/check/{wxToken}", method = RequestMethod.GET, produces = "text/plain")
    public @ResponseBody String validate(@PathVariable("wxToken") String wxToken, CheckModel tokenModel) throws ParseException, IOException {
        return tokenService.validate(wxToken, tokenModel);
    }
}
