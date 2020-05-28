package com.giang.Slytherin.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MessageRequest {

    @JsonProperty("fromid")
    private int fromid;

    @JsonProperty("toid")
    private int toid;

    @JsonProperty("content")
    private String content;

}
