package com.kennedy.springdemo.web.wechat;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;

import com.kennedy.springdemo.beans.wechat.message.InputMessage;
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
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public String doGet(HttpServletRequest request, HttpServletResponse response) {
        return tokenService.validate(request);
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST, produces = "text/xml")
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document d = db.parse(request.getInputStream());
        InputMessage inputMessage = new InputMessage();
        String msgType = d.getElementsByTagName("MsgType").item(0).getFirstChild().getNodeValue();
        switch (msgType) {
            case "text":
                inputMessage.setToUserName(d.getElementsByTagName("ToUserName").item(0).getFirstChild().getNodeValue());
                inputMessage.setFromUserName(d.getElementsByTagName("FromUserName").item(0).getFirstChild().getNodeValue());
                inputMessage.setCreateTime(d.getElementsByTagName("CreateTime").item(0).getFirstChild().getNodeValue());
                inputMessage.setMsgType(d.getElementsByTagName("MsgType").item(0).getFirstChild().getNodeValue());
                inputMessage.setContent(d.getElementsByTagName("Content").item(0).getFirstChild().getNodeValue());
                inputMessage.setMsgId(d.getElementsByTagName("MsgId").item(0).getFirstChild().getNodeValue());
                break;
            case "image":
                inputMessage.setToUserName(d.getElementsByTagName("ToUserName").item(0).getFirstChild().getNodeValue());
                inputMessage.setFromUserName(d.getElementsByTagName("FromUserName").item(0).getFirstChild().getNodeValue());
                inputMessage.setCreateTime(d.getElementsByTagName("CreateTime").item(0).getFirstChild().getNodeValue());
                inputMessage.setMsgType(d.getElementsByTagName("MsgType").item(0).getFirstChild().getNodeValue());
                inputMessage.setContent(d.getElementsByTagName("Content").item(0).getFirstChild().getNodeValue());
                inputMessage.setMsgId(d.getElementsByTagName("MsgId").item(0).getFirstChild().getNodeValue());
                break;
            case "voice":
                inputMessage.setToUserName(d.getElementsByTagName("ToUserName").item(0).getFirstChild().getNodeValue());
                inputMessage.setFromUserName(d.getElementsByTagName("FromUserName").item(0).getFirstChild().getNodeValue());
                inputMessage.setCreateTime(d.getElementsByTagName("CreateTime").item(0).getFirstChild().getNodeValue());
                inputMessage.setMsgType(d.getElementsByTagName("MsgType").item(0).getFirstChild().getNodeValue());
                inputMessage.setContent(d.getElementsByTagName("Content").item(0).getFirstChild().getNodeValue());
                inputMessage.setMsgId(d.getElementsByTagName("MsgId").item(0).getFirstChild().getNodeValue());
                break;
            case "video":
                inputMessage.setToUserName(d.getElementsByTagName("ToUserName").item(0).getFirstChild().getNodeValue());
                inputMessage.setFromUserName(d.getElementsByTagName("FromUserName").item(0).getFirstChild().getNodeValue());
                inputMessage.setCreateTime(d.getElementsByTagName("CreateTime").item(0).getFirstChild().getNodeValue());
                inputMessage.setMsgType(d.getElementsByTagName("MsgType").item(0).getFirstChild().getNodeValue());
                inputMessage.setContent(d.getElementsByTagName("Content").item(0).getFirstChild().getNodeValue());
                inputMessage.setMsgId(d.getElementsByTagName("MsgId").item(0).getFirstChild().getNodeValue());
                break;
            case "shortvideo":
                inputMessage.setToUserName(d.getElementsByTagName("ToUserName").item(0).getFirstChild().getNodeValue());
                inputMessage.setFromUserName(d.getElementsByTagName("FromUserName").item(0).getFirstChild().getNodeValue());
                inputMessage.setCreateTime(d.getElementsByTagName("CreateTime").item(0).getFirstChild().getNodeValue());
                inputMessage.setMsgType(d.getElementsByTagName("MsgType").item(0).getFirstChild().getNodeValue());
                inputMessage.setContent(d.getElementsByTagName("Content").item(0).getFirstChild().getNodeValue());
                inputMessage.setMsgId(d.getElementsByTagName("MsgId").item(0).getFirstChild().getNodeValue());
                break;
            case "location":
                inputMessage.setToUserName(d.getElementsByTagName("ToUserName").item(0).getFirstChild().getNodeValue());
                inputMessage.setFromUserName(d.getElementsByTagName("FromUserName").item(0).getFirstChild().getNodeValue());
                inputMessage.setCreateTime(d.getElementsByTagName("CreateTime").item(0).getFirstChild().getNodeValue());
                inputMessage.setMsgType(d.getElementsByTagName("MsgType").item(0).getFirstChild().getNodeValue());
                inputMessage.setContent(d.getElementsByTagName("Content").item(0).getFirstChild().getNodeValue());
                inputMessage.setMsgId(d.getElementsByTagName("MsgId").item(0).getFirstChild().getNodeValue());
                break;
            default:
                inputMessage = null;
                break;
        }
        return "";
    }

}
