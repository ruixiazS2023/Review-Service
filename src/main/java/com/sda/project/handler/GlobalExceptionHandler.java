package com.sda.project.handler;

import com.sda.project.common.Code;
import com.sda.project.common.ResponseEntity;
import com.sda.project.common.ResponseUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 *
 * This class is used to handle the global exceptions.
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseUtils.error(Code.PARAM_ERROR, e.getMessage());
    }

}
