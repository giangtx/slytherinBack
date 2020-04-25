package com.giang.Slytherin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "taikhoan")
@Data
public class TaiKhoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long MaTaiKhoan;
    private String TenDangNhap;
    private String MatKhau;
    private String Email;
    private Integer Active;
    private String Verification;
    private String AnhDaiDien;
    private String HoTen;
    private String ThanhPho;
    private String QuocGia;
    private String GioiTinh;
    private String NgaySinh;

    @OneToMany(mappedBy = "taikhoan",fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<HinhAnh> hinhanhs;

    @OneToMany(mappedBy = "taikhoan",fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Post> posts;

    @ManyToOne
    @JoinColumn(name = "MaChucVu")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ChucVu chucvu;

    @OneToMany(mappedBy = "taikhoan",fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<BinhLuan> binhluans;

    @OneToMany(mappedBy = "taikhoan",fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Thich> thiches;

    @OneToMany(mappedBy = "taikhoan",fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<PostLike> postLikes;

    @OneToMany(mappedBy = "taikhoan",fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<PostComment> postComments;
}
