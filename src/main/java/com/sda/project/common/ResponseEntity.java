package com.sda.project.common;

import lombok.Data;

@Data
public class ResponseEntity<T>{
    private int code;
    private String message;
    private T data;
    private String description;

    public ResponseEntity(Code code, T data, String description) {
        this.code = code.getCode();
        this.message = code.getMessage();
        this.data = data;
        this.description = description;
    }
}
