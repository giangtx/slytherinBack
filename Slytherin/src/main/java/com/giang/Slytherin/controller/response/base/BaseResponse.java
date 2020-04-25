package com.giang.Slytherin.controller.response.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    @JsonProperty("errorCode")  //  1  success   0 error dau vao   -1 -xxxxxx
    private Integer errorCode;
    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private T data;

    public BaseResponse() {
    }

    public BaseResponse(Integer errorCode, String message, T data) {
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }
    public BaseResponse(Integer errorCode,String message){
        this.errorCode=errorCode;
        this.message=message;
    }
    public BaseResponse(Builder builder) {
        this.errorCode = builder.errorCode;
        this.message = builder.message;
        this.data = (T) builder.data;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static class Builder<T> {
        private Integer errorCode;
        private String message;
        private T data;

        public Builder(Integer errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }

        public static Builder with(int errorCode, String message) {
            return new Builder(errorCode, message);
        }

        public Builder with(T data) {
            this.data = data;
            return this;
        }

        public BaseResponse<T> build() {
            return new BaseResponse<>(this);
        }
    }
}

