package com.giang.Slytherin.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HinhAnhResponse {

    @JsonProperty("mahinhanh")
    private long mahinhanh;

    @JsonProperty("resize")
    private String resize;
}
