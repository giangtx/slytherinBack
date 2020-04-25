package com.giang.Slytherin.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ThichRequest {
    @JsonProperty("mahinhanh")
    private int mahinhanh;
}
