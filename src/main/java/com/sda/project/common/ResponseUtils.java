package com.sda.project.common;

/**
 * This class is used to help generate response.
 */
public class ResponseUtils {
    public static <T> ResponseEntity<T> success(T data) {
        return new ResponseEntity<>(Code.SUCCESS, data, null);
    }

    public static <T> ResponseEntity<T> error(Code code, String description) {
        return new ResponseEntity<>(code, null, description);
    }

}
