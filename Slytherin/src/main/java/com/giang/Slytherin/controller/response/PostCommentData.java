package com.giang.Slytherin.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentData {
    @JsonProperty("id")
    private long id;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("username")
    private String username;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("timecomment")
    private java.sql.Timestamp timecomment;
}
