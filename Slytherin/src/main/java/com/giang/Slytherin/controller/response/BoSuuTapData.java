package com.giang.Slytherin.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.giang.Slytherin.model.BoSuuTap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoSuuTapData {

    @JsonProperty("mabosuutap")
    private long mabosuutap;

    @JsonProperty("tenbosuutap")
    private String tenbosuutap;

    @JsonProperty("anhbosuutap")
    private String anhbosuutap;

    @JsonProperty("mota")
    private String mota;
}
