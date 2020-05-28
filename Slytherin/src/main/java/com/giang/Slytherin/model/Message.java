package com.giang.Slytherin.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idmessage;

    private String message;
    private LocalDateTime timesend;
    private int sender;
    private int idchat;
}
