package net.tabplus.api.modules.validation;

import net.tabplus.api.exception.BaseException;
import net.tabplus.api.exception.ExceptionEnum;
import net.tabplus.api.modules.pojo.User;
import net.tabplus.api.utils.RegexConst;

/**
 * 用户校验类
 *
 * @author lihaoyu
 * @date 2019/9/17 14:56
 */
public class UserValidation extends BaseValidation {

    public static User validateRegistration(User user) throws BaseException {
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
     * @param
     * @return
     * @author lihaoyu
     * @date 2019/10/10 20:33
     */
    public static User validateUpdate(User user) throws BaseException {
        if (!isGender(user.getGender())) {
            throw new BaseException(ExceptionEnum.USER_GENDER_VALIDATION);
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
