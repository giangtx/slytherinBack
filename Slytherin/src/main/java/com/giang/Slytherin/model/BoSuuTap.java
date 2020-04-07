package com.giang.Slytherin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
@Table(name = "bosuutap")
public class BoSuuTap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long MaBoSuuTap;

    private String TenBoSuuTap;
    private String AnhBoSuuTap;
    private String MoTa;

    @OneToMany(mappedBy = "bosuutap",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<HinhAnh> hinhanhs;
}
