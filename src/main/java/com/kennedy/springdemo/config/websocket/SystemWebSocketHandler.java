package com.kennedy.springdemo.config.websocket;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.kennedy.springdemo.service.websocket.WebSocketService;

/**
 * @Description: 1、用户登录后建立websocket连接，默认选择websocket连接，如果浏览器不支持，则使用sockjs进行模拟连接
 *               2、建立连接后，服务端返回该用户的未读消息 3、服务端进行相关操作后，推送给某一个用户或者所有用户新消息
 * @date: 2016年8月5日 上午11:02:00
 * @author: zengt
 * @version: 1.0
 */
public class SystemWebSocketHandler extends TextWebSocketHandler {
    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>(); // 所有連接用戶的WebSocketSession
    @Autowired
    private WebSocketService webSocketService;

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        users.remove(session);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        users.add(session);
        String userName = (String) session.getAttributes().get("username");
        if (userName != null) {
            // 查询未读消息
            int count = webSocketService.getUnReadNews((String) session.getAttributes().get("username"));
            session.sendMessage(new TextMessage(count + ""));
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        session.sendMessage(message);
    }

    public static ArrayList<WebSocketSession> getUsers() {
        return users;
    }

    /**
     * 给所有在线用户发送消息
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给某个用户发送消息
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get("username").equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
