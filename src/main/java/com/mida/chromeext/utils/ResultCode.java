package com.mida.chromeext.utils;

public enum ResultCode {
    /**
     * 成功返回状态
     */
    SUCCESS(200, "Success"),

    /**
     * 失败请求
     */
    FAIL(422, "Fail"),

    /**
     * 请求格式错误
     */
    BAD_REQUEST(400, "BadRequest"),

    /**
     * 未授权
     */
    UNAUTHORIZED(401, "Unauthorized"),

    /**
     * 没有权限
     */
    FORBIDDEN(403, "Forbidden"),

    /**
     * 请求的资源不存在
     */
    NOT_FOUND(404, "ResourceNotExist"),

    /**
     * 该http方法不被允许
     */
    NOT_ALLOWED(405, "MethodNotAllowed"),

    /**
     * 请求处理发送异常
     */
    PROCESSING_EXCEPTION(406, "HandlingExceptions"),

    /**
     * 请求处理未完成
     */
    PROCESSING_UNFINISHED(407, "UnfinishedRequest"),

    /**
     * 登录过期
     */
    BEOVERDUE(408, "UserExpired"),

    /**
     * 用户未登录
     */
    NOT_LOGIN(409, "NotLoggedIn"),

    /**
     * 这个url对应的资源现在不可用
     */
    GONE(410, "ResourceGone"),

    /**
     * 请求类型错误
     */
    UNSUPPORTED_MEDIA_TYPE(415, "UnsupportedMediaType"),

    /**
     * 校验错误时用
     */
    UNPROCESSABLE_ENTITY(422, "UnprocessableEntity"),

    /**
     * 请求过多
     */
    TOO_MANY_REQUEST(429, "TooManyRequest");

    private int status;
    private String code = null;

    ResultCode(int status, String code) {
        this.status = status;
        this.code = code;
    }

    public static Boolean isValidateStateType(String... stateType) {
        for (int i = 0; i < stateType.length; i++) {
            ResultCode[] value = ResultCode.values();
            boolean falg = false;
            for (ResultCode type : value) {
                if (type.code.equals(stateType[i])) {
                    falg = true;
                }

            }
            if (!falg) {
                return falg;
            }
        }
        return true;
    }

    public String code() {
        return this.code;
    }

    public int status() {
        return status;
    }

    @Override
    public String toString() {
        return code;
    }
}

