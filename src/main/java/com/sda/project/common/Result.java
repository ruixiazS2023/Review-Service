package com.sda.project.common;
import lombok.Value;

@Value
public class Result {
    int code;
    Object data;
    String message;
}
