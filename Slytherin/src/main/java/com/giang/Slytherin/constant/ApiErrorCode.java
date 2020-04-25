package com.giang.Slytherin.constant;


/**
 * @author namkx
 * Create at 3/28/20
 */
public enum ApiErrorCode {
    UNAUTHORIZED(-1, "Unauthorized"),
    STRING_EMPTY(-2, "is empty"),
    INCORRECT_HEADER_REQUEST(-3, "Incorrect header request"),
    PHONE_INCORRECT(-4, "is incorrect phone"),
    EMAIL_INCORRECT(-5, "is incorrect email"),
    OBJECT_NOT_FOUND(-6, "not exist"),
    OBJECT_EXIST(-7, "is exist"),
    TOKEN_EXPIRED(-8, "is expired"),
    OTP_EXPIRED(-9, "is expired"),
    OTP_CONFIRM_FAILED(-10, "confirm otp failed"),
    ID_NOT_FOUND(-11, "id not exist"),
    DATE_INCORRECT_VALUE(-12, "is not set value"),
    DATA_NOT_FOUND(-12, "is not set value"),
    ;

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    private Integer errorCode;
    private String message;

    ApiErrorCode(Integer errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
