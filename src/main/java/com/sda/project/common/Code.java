package com.sda.project.common;

/**
 * This class is to define the response code and message.
 */
public enum Code {
    SUCCESS(20000, "success"),
    FAIL(50000, "fail"),
    PARAM_ERROR(40000, "param error"),
    NOT_FOUND(40400, "not found"),
    UNAUTHORIZED(40100, "unauthorized"),
    FORBIDDEN(40300, "forbidden"),
    SERVER_ERROR(500, "server error");

    private int code;
    private String message;


    Code(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
