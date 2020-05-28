package com.giang.Slytherin.repository;

import com.giang.Slytherin.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Long> {
    Chat findByIdchat(long id);
}
