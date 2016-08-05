package com.kennedy.springdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.kennedy.springdemo.beans.common.interceptor.WebSocketHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(marcoHandler(), "/marco").addInterceptors(new WebSocketHandshakeInterceptor());
    }

    @Bean
    public SystemWebSocketHandler marcoHandler() {
        return new SystemWebSocketHandler();
    }

}
