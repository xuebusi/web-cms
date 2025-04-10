package com.xuebusi.cms.api.common;

import lombok.Data;

@Data
public class ResponseResult<T> {
    private int code;
    private String message;
    private T data;

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(200, "success", data);
    }

    public static <T> ResponseResult<T> success(String message, T data) {
        return new ResponseResult<>(200, message, data);
    }

    public static ResponseResult<Void> success(String message) {
        return new ResponseResult<>(200, message, null);
    }

    public static ResponseResult<Void> error(String message) {
        return new ResponseResult<>(400, message, null);
    }

    public static ResponseResult<Void> error(int code, String message) {
        return new ResponseResult<>(code, message, null);
    }

    private ResponseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}