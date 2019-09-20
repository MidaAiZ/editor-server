package com.mida.chromeext.utils;

import java.util.HashMap;
import java.util.Map;


public class Result extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    private HashMap<String, Object> data = new HashMap<>();

    public Result() {
        setData(data);
        setCode(ResultCode.SUCCESS.code());
    }

    public Result(String code,String msg) {
        setData(data);
        setCode(code);
        setMsg(msg);
    }

    public static Result error() {
        return new Result(ResultCode.FAIL.code(), "");
    }

    public static Result error(String code) {
        return error(ResultCode.FAIL.code(), "");
    }

    public static Result error(String code, String msg) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);

        return r;
    }

    public static Result ok(String msg) {
        Result r = new Result();
        r.setMsg(msg);
        return r;
    }

    public static Result ok(Map<String, Object> map) {
        Result r = new Result();
        r.setData((HashMap) map);
        return r;
    }

    public static Result ok() {
        return new Result(ResultCode.SUCCESS.code(),"");
    }

    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public Result setCode(String code) {
        return this.put("code", code);
    }

    public Result setMsg(String msg) {
        return this.put("message", msg);
    }


    public Result setData(HashMap map) {
        this.put("data", map);
        if (!this.data.equals(map)) this.data = (HashMap)map.clone();
        return this;
    }

    public Result putData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}
