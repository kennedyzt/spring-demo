package com.kennedy.springdemo.web.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kennedy.springdemo.beans.user.User;
import com.kennedy.springdemo.common.ResultMsg;

@Controller
@RequestMapping("/demo")
public class DemoController {
    @RequestMapping("/bootstrapvalidator")
    public String toBootstrapValidator() {
        return "/demo/bootstrapValidator";
    }

    @RequestMapping("/layer")
    public String toLayer() {
        return "/demo/layer";
    }

    @RequestMapping("/angularjs/helloworld")
    public String toHelloWorld() {
        return "/demo/angularjs/helloWorld";
    }

    @RequestMapping("/fileinput")
    public String toFileInput() {
        return "/demo/fileInput";
    }

    @RequestMapping(value = "/fileinput/adduser", method = RequestMethod.POST)
    public @ResponseBody ResultMsg addUser(User user, @RequestParam("doc") MultipartFile file) {
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setIsSuccess(Boolean.TRUE);
        return resultMsg;
    }

}
