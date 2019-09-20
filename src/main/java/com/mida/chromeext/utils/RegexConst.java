package com.mida.chromeext.utils;

/**
 * @author lihaoyu
 * @date 2019/9/19 15:43
 */
public interface RegexConst {

    String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    String REGEX_USER_NUMBER = "^[a-zA-Z0-9_\\u4e00-\\u9fa5]{2,16}$";

    String REGEX_USER_PASSWORD = "^[a-zA-Z0-9.,!@#$%&'*+\\/\\]\\[=?^_`{|}~-]{6,20}$";
}
