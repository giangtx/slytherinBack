package com.giang.Slytherin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "post_like")
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idlike;

    @Column(name = "statuslike")
    private int statuslike;

    @Column(name = "timelike")
    private java.sql.Date timelike;

    @ManyToOne
    @JoinColumn(name = "MaTaiKhoan")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private TaiKhoan taikhoan;

    @ManyToOne
    @JoinColumn(name="idpost")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Post post;
}
