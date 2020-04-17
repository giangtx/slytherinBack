package com.giang.Slytherin.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BinhLuanData {
    @JsonProperty("mabinhluan")
    private long mabinhluan;

    @JsonProperty("binhluan")
    private String binhluan;

    @JsonProperty("tentaikhoan")
    private String tentaikhoan;

    @JsonProperty("anhdaidien")
    private String anhdaidien;
}
