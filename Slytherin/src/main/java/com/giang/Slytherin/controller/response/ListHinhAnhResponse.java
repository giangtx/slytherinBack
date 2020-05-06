package com.giang.Slytherin.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListHinhAnhResponse {

    @JsonProperty("sotrang")
    private int sotrang;

    @JsonProperty("listhinhanh")
    private List<HinhAnhResponse> listhinhanh;
}
