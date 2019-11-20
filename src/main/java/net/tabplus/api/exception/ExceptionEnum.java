package net.tabplus.api.exception;

/**
 * @author lihaoyu
 * @date 2019/9/19 14:02
 */
public enum ExceptionEnum {

    USER_PASSWORD_INVALID("10007", "密码不正确"),

    USER_REGISTER_EXIST_EMAIL("1006", "邮箱已存在"),

    USER_REGISTER_EXIST_NUMBER("1005", "账号已存在"),

    USER_REGISTER_PASSWORD("1004", "密码格式不正确"),

    USER_REGISTER_NUMBER("1003", "账号格式不正确"),

    EMAIL_VALIDATION("1002", "邮箱格式错误"),

    USER_EMAIL_NULL("1001", "登录邮箱不能为空"),

    USER_GENDER_VALIDATION("1000", "性别错误");

    private String code;

    private String message;

    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
