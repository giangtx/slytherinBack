package com.giang.Slytherin.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaiKhoanData {

    @JsonProperty("mataikhoan")
    private long mataikhoan;

    @JsonProperty("tendangnhap")
    private String tendangnhap;

    @JsonProperty("email")
    private String email;

    @JsonProperty("active")
    private int active;

    @JsonProperty("anhdaidien")
    private String anhdaidien;

    @JsonProperty("hoten")
    private String hoten;

    @JsonProperty("thanhpho")
    private String thanhpho;

    @JsonProperty("quocgia")
    private String quocgia;

    @JsonProperty("gioitinh")
    private String gioitinh;

    @JsonProperty("ngaysinh")
    private String ngaysinh;
}
