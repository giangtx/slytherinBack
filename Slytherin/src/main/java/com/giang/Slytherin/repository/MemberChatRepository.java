package com.giang.Slytherin.repository;

import com.giang.Slytherin.model.MemberChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberChatRepository extends JpaRepository<MemberChat,Long> {
    List<MemberChat> findAllByIduser(int id);
    List<MemberChat> findAllByIdchat(int id);
}
