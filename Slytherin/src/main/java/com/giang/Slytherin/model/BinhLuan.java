package com.giang.Slytherin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "binhluan")
@Data
public class BinhLuan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long MaTuongTac;

    private String BinhLuan;

    @ManyToOne
    @JoinColumn(name = "MaTaiKhoan")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private TaiKhoan taikhoan;

    @ManyToOne
    @JoinColumn(name = "MaHinhAnh")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private HinhAnh hinhanh;
}
