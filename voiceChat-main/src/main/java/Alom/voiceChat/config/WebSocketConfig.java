package Alom.voiceChat.config;

import Alom.voiceChat.rtc.KurentoHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import sun.misc.SignalHandler;

//@Configuration
//@EnableWebSocket
//@RequiredArgsConstructor
public class WebSocketConfig {// implements WebSocketConfigurer {
//
//    @Value("${kms.url}")
//    private String kmsUrl;
//
//
//    private final KurentoHandler signalHandler;
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
//        registry.addHandler(signalHandler, "/signal")
//                .setAllowedOrigins("*");
//    }
//
//    @Bean
//    public ServletServerContainerFactoryBean createWebSocketContainer(){
//        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
//        container.setMaxTextMessageBufferSize(8192);
//        container.setMaxBinaryMessageBufferSize(8192);
//
//        return container;
//    }
}
