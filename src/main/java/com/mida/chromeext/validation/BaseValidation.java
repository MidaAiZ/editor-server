package com.mida.chromeext.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 基础校验类
 * @author lihaoyu
 * @date 2019/9/17 14:38
 */
public class BaseValidation {
    /**
     *  邮箱校验
     *
     * @param email 字符串
     * @return boolean
     * @author lihaoyu
     * @date 2019/9/17 14:48
     */
    public static boolean isEmail(String email) {
        if (null == email || "".equals(email)) {
            return false;
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(email);
        if (m.matches()) {
            return true;
        }
        else {
            return false;
        }

    }

}
