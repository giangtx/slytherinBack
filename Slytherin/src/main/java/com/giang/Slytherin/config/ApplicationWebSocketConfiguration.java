package com.giang.Slytherin.config;

import com.giang.Slytherin.service.TaiKhoanServiceImp;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.Optional;

@Configuration
@EnableWebSocketMessageBroker
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class ApplicationWebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
    private final TaiKhoanServiceImp taiKhoanServiceImp;

    public ApplicationWebSocketConfiguration(TaiKhoanServiceImp taiKhoanServiceImp) {
        this.taiKhoanServiceImp = taiKhoanServiceImp;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Register the "/socket" endpoint, enabling the SockJS protocol.
        // SockJS is used (both client and server side) to allow alternative
        // messaging options if WebSocket is not available.
        registry.addEndpoint("/socket")
                .setAllowedOrigins("*")
                .withSockJS();
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
//    // Enable a simple memory-based message broker to send messages to the
//    // client on destinations prefixed with "/app".
//    // Simple message broker handles subscription requests from clients, stores
//    // them in memory, and broadcasts messages to connected clients with
//    // matching destinations.
        registry.setApplicationDestinationPrefixes("/app")
                .setUserDestinationPrefix("/user")
                .enableSimpleBroker("/chat", "/topic", "/queue");
    }
}
