package com.giang.Slytherin.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatData {

    @JsonProperty("idchat")
    private long idchat;

    @JsonProperty("name")
    private String name;

    @JsonProperty("image")
    private String image;

    @JsonProperty("lastmessage")
    private String lastmessage;

    @JsonProperty("lastsender")
    private String lastsender;

    @JsonProperty("time")
    private LocalDateTime time;

    @JsonProperty("type")
    private int type;
}
