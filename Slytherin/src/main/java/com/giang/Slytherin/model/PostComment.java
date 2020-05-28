package com.giang.Slytherin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "post_comment")
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idcomment;

    @Column(name = "content")
    private String content;

    @Column(name = "timecomment")
    private java.sql.Timestamp timecomment;

    @ManyToOne
    @JoinColumn(name = "MaTaiKhoan")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private TaiKhoan taikhoan;

    @ManyToOne
    @JoinColumn(name = "idpost")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Post post;
}
