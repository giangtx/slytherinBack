package com.giang.Slytherin.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "thich")
@Data
public class Thich {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaThich;

    private Integer TrangThai;

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
