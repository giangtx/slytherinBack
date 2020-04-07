package com.giang.Slytherin.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.giang.Slytherin.model.HinhAnh;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListHinhAnhData {
    @JsonProperty("listhinhanh")
    private List<HinhAnhData> listhinhanh;
}
