package com.giang.Slytherin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "chucvu")
@Data
public class ChucVu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long MaChucVu;
    private String TenChucVu;

    @OneToMany(mappedBy = "chucvu",fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<TaiKhoan> taiKhoans;
}
