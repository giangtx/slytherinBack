package com.giang.Slytherin.service;

import com.giang.Slytherin.controller.request.MessageRequest;
import com.giang.Slytherin.controller.response.MessageData;
import com.giang.Slytherin.model.CustomUserDetails;
import com.giang.Slytherin.model.Message;
import com.giang.Slytherin.model.TaiKhoan;
import com.giang.Slytherin.repository.MessageRepository;
import com.giang.Slytherin.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImp {
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    TaiKhoanRepository taiKhoanRepository;

    public List<MessageData> getMessageAllByIdChat(int idchat){
        CustomUserDetails customUserDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<MessageData> response = new ArrayList<>();
        List<Message> messageList = messageRepository.findAllByIdchat(idchat);
        for (Message mesage : messageList){
            MessageData data = new MessageData();
            data.setIdmessage(mesage.getIdmessage());
            data.setIdchat(idchat);
            data.setContent(mesage.getMessage());
            data.setIdsender(mesage.getSender());
            data.setTime(mesage.getTimesend());
            TaiKhoan taiKhoan = taiKhoanRepository.findById(mesage.getSender());
            data.setNamesender(taiKhoan.getTenDangNhap());
            data.setSenderimage(taiKhoan.getAnhDaiDien());
            if(mesage.getSender()==customUserDetails.getTaikhoan().getMaTaiKhoan()){
                data.setTypeuser(1);
            }else{
                data.setTypeuser(2);
            }
            response.add(data);
        }
        return response;
    }
    public MessageData createMessage(MessageRequest messageRequest){
        CustomUserDetails customUserDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Message message = new Message();
        message.setIdchat(messageRequest.getToid());
        message.setMessage(messageRequest.getContent());
        message.setSender(messageRequest.getFromid());
        message.setTimesend(LocalDateTime.now());
        Message messageSave = messageRepository.save(message);
        MessageData data=new MessageData();
        if(messageSave!=null){
            data.setIdmessage(message.getIdmessage());
            data.setIdchat(message.getIdchat());
            data.setContent(message.getMessage());
            data.setIdsender(message.getSender());
            data.setTime(message.getTimesend());
            TaiKhoan taiKhoan = taiKhoanRepository.findById(message.getSender());
            data.setNamesender(taiKhoan.getTenDangNhap());
            data.setSenderimage(taiKhoan.getAnhDaiDien());
            if(message.getSender()==customUserDetails.getTaikhoan().getMaTaiKhoan()){
                data.setTypeuser(1);
            }else{
                data.setTypeuser(2);
            }
            return data;
        }else
            return null;
    }
}
