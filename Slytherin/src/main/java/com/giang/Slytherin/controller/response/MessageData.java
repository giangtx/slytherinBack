package com.giang.Slytherin.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageData {

    @JsonProperty("idmessage")
    private long idmessage;

    @JsonProperty("idchat")
    private int idchat;

    @JsonProperty("idsender")
    private int idsender;

    @JsonProperty("content")
    private String content;

    @JsonProperty("namesender")
    private String namesender;

    @JsonProperty("senderimage")
    private String senderimage;

    @JsonProperty("time")
    private LocalDateTime time;

    @JsonProperty("typeuser")
    private int typeuser;
}
