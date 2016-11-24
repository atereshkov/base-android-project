package com.github.handioq.baseandroid.model.dto;

import android.text.TextUtils;

/**
 * HTTP 500 Internal Server Error
 * {
 * "errorCode": "XXX", // some predefined constant specific for the call or null
 * "message": "Error description to show to a user or log"
 * }
 */

//public class BaseResponse extends Entity

public class BaseResponse {
    private String message;
    private String errorCode;

    public BaseResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return TextUtils.isEmpty(message);
    }

    public String toError() {
        return "HANDLED SERVER ERROR {" +
                "message='" + message + '\'' +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
