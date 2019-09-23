package com.mida.chromeext.validation;

import com.mida.chromeext.exception.BaseException;
import com.mida.chromeext.exception.ExceptionEnum;
import com.mida.chromeext.pojo.User;
import com.mida.chromeext.utils.RegexConst;

/**
 * 用户注册校验类
 *
 * @author lihaoyu
 * @date 2019/9/17 14:56
 */
public class UserValidation extends BaseValidation {

    public static User validateUser(User user) throws BaseException {
        if (!isGender(user.getGender())) {
            throw new BaseException(ExceptionEnum.USER_GENDER_VALIDATION);
        }
        if (!isEmail(user.getEmail())) {
            throw new BaseException(ExceptionEnum.EMAIL_VALIDATION);
        }
        if (!isPassWord(user.getPassword())) {
            throw new BaseException(ExceptionEnum.USER_REGISTER_PASSWORD);
        }
        if (!isNumber(user.getNumber())) {
            throw new BaseException(ExceptionEnum.USER_REGISTER_NUMBER);
        }
        return user;
    }

    /**
     * 验证性别 0:男 1:女
     *
     * @param gender 性别
     * @return boolean
     * @author lihaoyu
     * @date 2019/9/17 15:03
     */
    public static boolean isGender(Byte gender) {
        return gender == null || gender.byteValue() == 0 || gender.byteValue() == 1;
    }

    /**
     * 验证密码是否格式正确
     *
     * @param pwd 密码字符串
     * @return boolean
     * @author lihaoyu
     * @date 2019/9/19 15:37
     */
    public static boolean isPassWord(String pwd) {
        String regex = RegexConst.REGEX_USER_PASSWORD;
        return regexMatch(pwd, regex);
    }

    public static boolean isNumber(String number) {
        String regex = RegexConst.REGEX_USER_NUMBER;
        return regexMatch(number, regex);
    }
}
