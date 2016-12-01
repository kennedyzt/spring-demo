package com.kennedy.springdemo.web.wechat;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.kennedy.springdemo.beans.wechat.message.Filter;
import com.kennedy.springdemo.beans.wechat.message.InputMessage;
import com.kennedy.springdemo.beans.wechat.message.SendAllMsg;
import com.kennedy.springdemo.beans.wechat.message.Text;
import com.kennedy.springdemo.service.wechat.TokenService;
import com.kennedy.springdemo.utils.WeChatUtil;

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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Document d = buildDocument(request);
        InputMessage inputMessage = buildInputMessage(d);
        switch (inputMessage.getMsgType()) {
            case "text":
                inputMessage.setContent(d.getElementsByTagName("Content").item(0).getFirstChild().getNodeValue());
                inputMessage.setMsgId(d.getElementsByTagName("MsgId").item(0).getFirstChild().getNodeValue());
                break;
            case "image":
                break;
            case "voice":
                break;
            case "video":
                break;
            case "shortvideo":
                break;
            case "location":
                break;
            case "event":
                // 订阅
                if (inputMessage.getEvent().equals("subscribe")) {
                    SendAllMsg sendAllMsg = new SendAllMsg();
                    Filter filter = new Filter();
                    filter.setIs_to_all(true);
                    sendAllMsg.setFilter(filter);
                    sendAllMsg.setText(new Text("有新用户加入啦。。。"));
                    sendAllMsg.setMsgtype("text");
                    WeChatUtil.sendMsg(WeChatUtil.getToken().getAccessToken(), sendAllMsg);
                }
            default:
                inputMessage = null;
                break;
        }
    }

    private InputMessage buildInputMessage(Document d) {
        InputMessage inputMessage = new InputMessage();
        inputMessage.setToUserName(d.getElementsByTagName("ToUserName").item(0).getFirstChild().getNodeValue());
        inputMessage.setFromUserName(d.getElementsByTagName("FromUserName").item(0).getFirstChild().getNodeValue());
        inputMessage.setCreateTime(d.getElementsByTagName("CreateTime").item(0).getFirstChild().getNodeValue());
        inputMessage.setMsgType(d.getElementsByTagName("MsgType").item(0).getFirstChild().getNodeValue());
        inputMessage.setEvent(d.getElementsByTagName("Event").item(0).getFirstChild().getNodeValue());
        return inputMessage;
    }

    private Document buildDocument(HttpServletRequest request) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document d = db.parse(request.getInputStream());
        return d;
    }

}
