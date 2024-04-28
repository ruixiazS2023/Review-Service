package com.sda.project.common;

import lombok.Data;

/**
 * This class is used to store the response of the API.
 */
@Data
public class ResponseEntity<T>{
    // The code of the response.
    private int code;
    // The message of the response.
    private String message;
    // The data of the response.
    private T data;
    // The description of the response.
    private String description;

    public ResponseEntity(Code code, T data, String description) {
        this.code = code.getCode();
        this.message = code.getMessage();
        this.data = data;
        this.description = description;
    }
}
