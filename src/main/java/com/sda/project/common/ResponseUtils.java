package com.sda.project.common;

/**
 * This class is used to help generate response.
 */
public class ResponseUtils {
    /**
     * This method is used to generate success response.
     *
     * @param data the data to be returned
     * @param <T>  the type of the data
     * @return the response entity
     */
    public static <T> ResponseEntity<T> success(T data) {
        return new ResponseEntity<>(Code.SUCCESS, data, null);
    }

    /**
     * This method is used to generate error response.
     *
     * @param code        the code of the error
     * @param description the description of the error
     * @param <T>         the type of the data
     * @return the response entity
     */
    public static <T> ResponseEntity<T> error(Code code, String description) {
        return new ResponseEntity<>(code, null, description);
    }

}
