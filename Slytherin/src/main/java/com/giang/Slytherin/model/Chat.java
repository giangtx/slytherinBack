package com.giang.Slytherin.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat")
@Data
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idchat;
    private String namechat;
    private String image;
    private LocalDateTime timecreate;
    private int typechat;
}
