package com.giang.Slytherin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giang.Slytherin.controller.request.MessageRequest;
import com.giang.Slytherin.controller.response.ChatData;
import com.giang.Slytherin.controller.response.MessageData;
import com.giang.Slytherin.controller.response.base.BaseResponse;
import com.giang.Slytherin.controller.response.base.ResponseImpl;
import com.giang.Slytherin.model.Chat;
import com.giang.Slytherin.model.MemberChat;
import com.giang.Slytherin.service.ChatServiceImp;
import com.giang.Slytherin.service.MessageServiceImp;
import com.giang.Slytherin.service.TaiKhoanServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MessageController {
    @Autowired
    MessageServiceImp messageServiceImp;

    private final SimpMessagingTemplate template;
    private final ObjectMapper objectMapper;

    @Autowired
    ChatServiceImp chatServiceImp;

    @Autowired
    TaiKhoanServiceImp taiKhoanServiceImp;

    public MessageController(SimpMessagingTemplate template, ObjectMapper objectMapper) {
        this.template = template;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/auth/chat/getAll")
    public ResponseEntity<BaseResponse<List<ChatData>>> getAllChat(){
        return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok")
                .with(chatServiceImp.findAllChatById()).build());
    }

    @GetMapping("/auth/message/getAll/{id}")
    public ResponseEntity<BaseResponse<List<MessageData>>> getAllByIdchat(@PathVariable("id") int id){
        return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok")
                .with(messageServiceImp.getMessageAllByIdChat(id)).build());
    }
    @GetMapping("/auth/chat/getRecive/{id}")
    public ResponseEntity<BaseResponse<ChatData>> getReciveByIdchat(@PathVariable("id") int id){
        return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok")
                .with(chatServiceImp.getReceiver(id)).build());
    }
    @MessageMapping("/message")
    public void createPrivateChatMessages(@RequestBody @Valid MessageRequest messageRequest, Principal principal, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        MessageData message = messageServiceImp.createMessage(messageRequest);
        if (message != null) {
//            String response = this.objectMapper.writeValueAsString(message);
            List<MemberChat> memberChatList = chatServiceImp.findMemberChatById(message.getIdchat());
            for (MemberChat memberChat:memberChatList){
                String name = taiKhoanServiceImp.getUserById(memberChat.getIduser()).getTenDangNhap();
                template.convertAndSend("/user/" + name + "/queue/position-update", message);
            }

            return;
        }
        throw new Exception("Message Error");
    }
}
