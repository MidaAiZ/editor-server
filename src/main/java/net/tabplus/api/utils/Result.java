package net.tabplus.api.utils;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private static final long serialVersionUID = -8782333365744933352L;

    private String code;
    private String message = "";
    private boolean success = true;
    private T data;

    private Result() {
        this.code = ResultCode.SUCCESS.code();
    }

    private Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private Result(String code, T data, String message) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> error() {
        return error(ResultCode.FAIL.code(), "System error or illegal request");
    }

    public static <T> Result<T> error(String message) {
        return error(ResultCode.FAIL.code(), message);
    }

    public static <T> Result<T> error(String code, String message) {
        Result result = new Result(code, message);
        result.setSuccess(false);
        return result;
    }

    public static <T> Result<T> error(String code, T data, String message) {
        Result result = new Result(code, data, message);
        result.setSuccess(false);
        return result;
    }

    public static <T> Result<T> ok() {
        return ok(ResultCode.SUCCESS.code(), null, "");
    }

    public static <T> Result<T> ok(T data) {
        return ok(ResultCode.SUCCESS.code(), data);
    }

    public static <T> Result<T> ok(String code) {
        return ok(code, null);
    }

    public static <T> Result<T> ok(String code, T data) {
        return ok(ResultCode.SUCCESS.code(), data, "");
    }

    public static <T> Result<T> ok(String code, T data, String message) {
        return new Result(code, data, message);
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
