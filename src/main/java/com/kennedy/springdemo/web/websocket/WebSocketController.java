package com.kennedy.springdemo.web.websocket;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.TextMessage;

import com.kennedy.springdemo.config.websocket.SystemWebSocketHandler;

@Controller
@RequestMapping("/websocket")
public class WebSocketController {
    @Autowired
    private SystemWebSocketHandler systemWebSocketHandler;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String send(HttpServletRequest request) {
        TextMessage message = new TextMessage("你好");
        systemWebSocketHandler.sendMessageToUsers(message);
        return "redirect:/send";
    }

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String toSend() {
        return "/websocket/send";
    }
}
