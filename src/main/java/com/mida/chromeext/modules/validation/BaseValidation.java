package com.mida.chromeext.modules.validation;

import com.mida.chromeext.utils.RegexConst;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 基础校验类
 *
 * @author lihaoyu
 * @date 2019/9/17 14:38
 */
public class BaseValidation {
    /**
     * 邮箱校验
     *
     * @param email 字符串
     * @return boolean
     * @author lihaoyu
     * @date 2019/9/17 14:48
     */
    public static boolean isEmail(String email) {
        String regex = RegexConst.REGEX_EMAIL;
        return regexMatch(email, regex);
    }

    public static boolean regexMatch(String sourceString, String regex) {
        if (null == sourceString || "".equals(sourceString)) {
            return false;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(sourceString);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

}
