package com.giang.Slytherin.websocket;

import com.giang.Slytherin.model.TaiKhoan;
import com.giang.Slytherin.service.TaiKhoanServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import static com.giang.Slytherin.websocket.WebSocketEventName.*;
@Component
public class WebSocketEventListener {
    private final TaiKhoanServiceImp taiKhoanServiceImp;
    private final SimpMessagingTemplate template;

    @Autowired
    public WebSocketEventListener(TaiKhoanServiceImp taiKhoanServiceImp, SimpMessagingTemplate template) {
        this.taiKhoanServiceImp = taiKhoanServiceImp;
        this.template = template;
    }

    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) throws Exception {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String username = headers.getUser().getName();
        TaiKhoan taiKhoan=taiKhoanServiceImp.findByTenDangNhap(username);
        long userId = taiKhoan.getMaTaiKhoan();
        WebSocketMessage message = new WebSocketMessage(CONNECT, userId, username, true);

        template.convertAndSend(CONNECT.getDestination(), message);
    }

    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) throws Exception {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String username = headers.getUser().getName();

        TaiKhoan taiKhoan=taiKhoanServiceImp.findByTenDangNhap(username);
        long userId = taiKhoan.getMaTaiKhoan();
        WebSocketMessage message = new WebSocketMessage(DISCONNECT, userId, username, false);

        template.convertAndSend(DISCONNECT.getDestination(), message);
    }
}
