package com.giang.Slytherin.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BinhLuanRequest {

    @JsonProperty("mahinhanh")
    private int mahinhanh;

    @JsonProperty("binhluan")
    private String binhluan;
}
