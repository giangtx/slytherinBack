package com.giang.Slytherin.controller.base;

import com.giang.Slytherin.controller.response.base.BaseResponse;
import com.giang.Slytherin.controller.response.base.ResponseImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    private Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<BaseResponse> handlerApiException(ApiException ex) {
        LOGGER.error(" detail : {}", ex.getObjectValue());

        if (null != ex.getObjectName()) {
            String message = ex.getObjectName() + " " + ex.getApiErrorCode().getMessage();
            return build(ex.getApiErrorCode().getErrorCode(), message);
        }

        if (null != ex.getObjectValue()) {
            String message = ex.getObjectValue() + " " + ex.getApiErrorCode().getMessage();
            return build(ex.getApiErrorCode().getErrorCode(), message);
        }

        return build(ex.getApiErrorCode().getErrorCode(), ex.getApiErrorCode().getMessage());

    }

    public ResponseEntity<BaseResponse> build(Integer errorCode, String message) {
        return ResponseEntity.ok().body(
                ResponseImpl.ok().with(errorCode, message).build());
    }
}
