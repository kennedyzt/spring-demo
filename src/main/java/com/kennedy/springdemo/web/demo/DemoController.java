package com.kennedy.springdemo.web.demo;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kennedy.springdemo.beans.common.FileResponse;
import com.kennedy.springdemo.beans.user.User;
import com.kennedy.springdemo.common.ResultMsg;
import com.kennedy.springdemo.utils.FileUploadUtil;
import com.kennedy.springdemo.utils.ZipUtil;

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
    @ResponseBody
    public ResultMsg addUser(User user, @RequestParam("doc") MultipartFile file) {
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setIsSuccess(Boolean.TRUE);
        FileResponse fileResponse = FileUploadUtil.saveImage(file, "E:\\static\\img");
        String targetPath = "E:\\static\\img\\" + fileResponse.getNewFileName();
        try {
            ZipUtil.unZip(targetPath, "E:\\static\\img\\");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMsg;
    }

    @RequestMapping("/preview")
    public String toPreview() {
        return "/demo/preview";
    }

    @RequestMapping("/ueditor")
    public String toUeditor() {
        return "/demo/ueditor";
    }
}
