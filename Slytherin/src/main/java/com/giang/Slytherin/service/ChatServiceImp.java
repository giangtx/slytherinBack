package com.giang.Slytherin.service;

import com.giang.Slytherin.controller.response.ChatData;
import com.giang.Slytherin.model.*;
import com.giang.Slytherin.repository.ChatRepository;
import com.giang.Slytherin.repository.MemberChatRepository;
import com.giang.Slytherin.repository.MessageRepository;
import com.giang.Slytherin.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImp {

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    MemberChatRepository memberChatRepository;

    @Autowired
    TaiKhoanRepository taiKhoanRepository;

    @Autowired
    MessageRepository messageRepository;

    public List<ChatData> findAllChatById(){
        CustomUserDetails customUserDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ChatData> response = new ArrayList<>();
        List<MemberChat> memberChatList=memberChatRepository.
                findAllByIduser((int)customUserDetails.getTaikhoan().getMaTaiKhoan());
        for (MemberChat memberChat:memberChatList) {
            ChatData data = new ChatData();
            Chat chat = chatRepository.findByIdchat(memberChat.getIdchat());
            data.setIdchat(chat.getIdchat());
            if(memberChat.getTypechat()==1){
                List<MemberChat> listMember=memberChatRepository.findAllByIdchat(memberChat.getIdchat());
                for (MemberChat member:listMember) {
                    if(member.getIduser()!=customUserDetails.getTaikhoan().getMaTaiKhoan()){
                        TaiKhoan tk=taiKhoanRepository.findById(member.getIduser());
                        data.setName(tk.getTenDangNhap());
                        data.setImage(tk.getAnhDaiDien());
                    }
                }
            }else{
                data.setName(chat.getNamechat());
                data.setImage(chat.getImage());
            }
            data.setType(chat.getTypechat());

            Message lastMessage = messageRepository.findLastMessageByIdchat(chat.getIdchat());
            if (lastMessage!=null){
                TaiKhoan taiKhoan = taiKhoanRepository.findById(lastMessage.getSender());
                data.setLastsender(taiKhoan.getTenDangNhap());
                data.setLastmessage(lastMessage.getMessage());
                data.setTime(lastMessage.getTimesend());
            }

            response.add(data);
        }
        return response;
    }
    public ChatData getReceiver(int idchat){
        CustomUserDetails customUserDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ChatData data = new ChatData();
        Chat chat = chatRepository.findByIdchat(idchat);
        data.setIdchat(chat.getIdchat());
        data.setType(chat.getTypechat());
        if(chat.getTypechat()==1){
            List<MemberChat> listMember=memberChatRepository.findAllByIdchat(idchat);
            for (MemberChat member:listMember) {
                if(member.getIduser()!=customUserDetails.getTaikhoan().getMaTaiKhoan()){
                    TaiKhoan tk=taiKhoanRepository.findById(member.getIduser());
                    data.setName(tk.getTenDangNhap());
                    data.setImage(tk.getAnhDaiDien());
                }
            }
        }else{
            data.setName(chat.getNamechat());
            data.setImage(chat.getImage());
        }
        return data;
    }
    public List<MemberChat> findMemberChatById(int id){
        return memberChatRepository.findAllByIdchat(id);
    }
}
