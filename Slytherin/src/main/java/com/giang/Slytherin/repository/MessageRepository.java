package com.giang.Slytherin.repository;

import com.giang.Slytherin.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

    List<Message> findAllByIdchat(int idchat);

    @Query(value = "select * from message where idchat=:id order by idmessage DESC limit 1",nativeQuery = true)
    Message findLastMessageByIdchat(long id);
}
