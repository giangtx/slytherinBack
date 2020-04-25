package com.giang.Slytherin.controller.response.base;

public class ResponseImpl implements Response {
    private int errorCode;
    private String message;
    private Object data;

    public static Response ok() {
        return new ResponseImpl();
    }

    @Override
    public Response with(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        return this;
    }

    @Override
    public Response with(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public <T> BaseResponse<T> build() {
        return new BaseResponse<T>(errorCode, message, (T) data);
    }
}