package com.nexus.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {

    private int code;
    private String message;
    private T data;

    public static Result success_code_message(int code, String message) {
        return new Result(code, message,null);
    }

    public static Result fail_code_message(int code, String message) {
        return new Result(code, message,null);
    }
}
