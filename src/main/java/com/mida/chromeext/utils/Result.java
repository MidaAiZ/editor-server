package com.mida.chromeext.utils;

import java.util.HashMap;
import java.util.Map;


public class Result extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    private HashMap<String, Object> data;

    public Result() {

        put("code", ResultCode.SUCCESS);
    }

    public Result(String code,String msg) {
        put("code", code);
        put("msg", msg);
    }

    public static Result error() {
        return new Result(ResultCode.FAIL.code(), "");
    }

    public static Result error(String code) {
        return error(ResultCode.FAIL.code(), "");
    }

    public static Result error(String code, String msg) {
        Result r = new Result();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static Result ok(String msg) {
        Result r = new Result();
        r.put("msg", msg);
        return r;
    }

    public static Result ok(Map<String, Object> map) {
        Result r = new Result();
        r.data.putAll(map);
        return r;
    }

    public static Result ok() {
        return new Result(ResultCode.SUCCESS.code(),"");
    }

    public Result put(String key, Object value) {
        data.put(key, value);
        return this;
    }
}
