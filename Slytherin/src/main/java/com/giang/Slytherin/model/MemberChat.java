package com.giang.Slytherin.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "member_chat")
@Data
public class MemberChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idmember;

    private int idchat;
    private int iduser;
    private int userrole;
    private int typechat;
}
