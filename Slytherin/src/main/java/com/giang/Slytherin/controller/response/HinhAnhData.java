package com.giang.Slytherin.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.giang.Slytherin.model.BinhLuan;
import com.giang.Slytherin.model.HinhAnh;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HinhAnhData {

    @JsonProperty("mahinhanh")
    private long mahinhanh;

    @JsonProperty("tenhinhanh")
    private String tenhinhanh;

    @JsonProperty("resize")
    private String resize;

    @JsonProperty("kichco")
    private String kichco;

    @JsonProperty("mota")
    private String mota;

    @JsonProperty
    private long mabosuutap;

    @JsonProperty("tenbosuutap")
    private String tenbosuutap;

    @JsonProperty("tendangnhap")
    private String tendangnhap;

    @JsonProperty("anhdaidien")
    private String anhdaidien;

    @JsonProperty("mathich")
    private long mathich;

    @JsonProperty("trangthaithich")
    private int trangthaithich;

    @JsonProperty("listbinhluan")
    private List<BinhLuanData> listbinhluan;

    @JsonProperty("solike")
    private int solike;

    @JsonProperty("sobinhluan")
    private int sobinhluan;

}
