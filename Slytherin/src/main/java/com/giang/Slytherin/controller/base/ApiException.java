package com.giang.Slytherin.controller.base;

import com.giang.Slytherin.constant.ApiErrorCode;
import com.giang.Slytherin.utils.TextUtils;

public class ApiException extends RuntimeException {
    private String objectName;
    private Object objectValue;

    private ApiErrorCode apiErrorCode;


    public ApiException(String objectName, Object objectValue, ApiErrorCode apiErrorCode) {
        this.objectName = objectName;
        this.objectValue = objectValue;
        this.apiErrorCode = apiErrorCode;
    }

    public ApiException(Object objectValue, ApiErrorCode apiErrorCode) {
        this.apiErrorCode = apiErrorCode;
        this.objectValue = objectValue;
    }

    public ApiException(String objectName, ApiErrorCode apiErrorCode) {
        this.apiErrorCode = apiErrorCode;
        this.objectName = objectName;
    }

    public ApiException(ApiErrorCode apiErrorCode) {
        super();
        this.apiErrorCode = apiErrorCode;
    }

    public ApiErrorCode getApiErrorCode() {
        return apiErrorCode;
    }

    public void setApiErrorCode(ApiErrorCode apiErrorCode) {
        this.apiErrorCode = apiErrorCode;
    }

    public String getObjectName() {
        return objectName;
    }

    public Object getObjectValue() {
        return objectValue;
    }


    public static void validStringEmpty(String objectName, String value) {
        if (TextUtils.isEmpty(value)) {
            throw new ApiException(objectName, ApiErrorCode.STRING_EMPTY);
        }
    }

    public static void validPhoneNumber(String phone) {
        if (!TextUtils.isValidPhoneNumber(phone)) {
            throw new ApiException(phone, ApiErrorCode.PHONE_INCORRECT);
        }
    }

    public static void validEmail(String email) {
        if (!TextUtils.isValidEmail(email)) {
            throw new ApiException(email, ApiErrorCode.EMAIL_INCORRECT);
        }
    }

    public static String checkToken(String token) {
        if (TextUtils.isEmpty(token)) {
            throw new ApiException("token", ApiErrorCode.OBJECT_NOT_FOUND);
        }
        if (!token.startsWith("Bearer ")) {
            throw new ApiException("token", ApiErrorCode.INCORRECT_HEADER_REQUEST);
        }
        return token.replace("Bearer ", "");
    }

    public static void checkTokenExpired(Long tokenExpired) {
        if (tokenExpired < System.currentTimeMillis()) {
            throw new ApiException("token", ApiErrorCode.TOKEN_EXPIRED);
        }
    }

    public static void validID(String id) {
        if (!TextUtils.isNumeric(id)) {
            throw new ApiException(id, ApiErrorCode.ID_NOT_FOUND);
        }
    }

    public static void idNotFound(String id) {
        throw new ApiException(id, ApiErrorCode.ID_NOT_FOUND);
    }
}

