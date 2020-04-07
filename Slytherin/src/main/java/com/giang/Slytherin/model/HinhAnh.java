package com.giang.Slytherin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
@Table(name="hinhanh")
public class HinhAnh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long MaHinhAnh;
    private String TenHinhAnh;
    private String KichCo;
    private String DoPhanGiai;
    private String MoTaHinhAnh;
    private Integer AnhTaiTro;
    private Integer PheDuyet;
    private String Resize;
    private String NgayDang;
    private Integer likes;
    private Integer comments;

    @ManyToOne
    @JoinColumn(name = "MaBoSuuTap")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private BoSuuTap bosuutap;

    @ManyToOne
    @JoinColumn(name="MaTaiKhoan")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private TaiKhoan taikhoan;

    @OneToMany(mappedBy = "hinhanh",fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<BinhLuan> binhLuans;

    @OneToMany(mappedBy = "hinhanh",fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Thich> thichs;

}
