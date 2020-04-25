package com.giang.Slytherin.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostData {

    @JsonProperty("idpost")
    private long idpost;

    @JsonProperty("content")
    private String content;

    @JsonProperty("likes")
    private int likes;

    @JsonProperty("comments")
    private int comments;

    @JsonProperty("timepost")
    private java.sql.Date timepost;

    @JsonProperty("username")
    private String username;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("statuslike")
    private int statuslike;
}
