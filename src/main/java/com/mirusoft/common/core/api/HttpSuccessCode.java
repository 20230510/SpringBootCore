package com.mirusoft.common.core.api;

public enum HttpSuccessCode {

    SELECT_SUCCESS(200, "200", "Select success"),
    DELETE_SUCCESS(200, "200", "Delete success"),
    INSERT_SUCCESS(200, "200", "Insert success"),
    UPDATE_SUCCESS(200, "200", "Update success");

    /* 코드 상태*/
    private final int status;

    /* 코드*/
    private final String code;

    private final String message;

    HttpSuccessCode(final int status, final String code, final String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }

    // Getters for status, code, and message (if needed)
    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
