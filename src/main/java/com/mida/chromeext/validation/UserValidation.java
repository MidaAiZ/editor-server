package com.mida.chromeext.validation;

/**
 * 用户注册校验类
 * 
 * @author lihaoyu
 * @date 2019/9/17 14:56
 */
public class UserValidation extends BaseValidation {

    /**
     * 验证性别 0:男 1:女
     *
     * @param gender 性别
     * @return boolean
     * @author lihaoyu
     * @date 2019/9/17 15:03
     */
    public static boolean isGender(Integer gender) {
        return gender != null || gender.intValue() == 0 || gender.intValue() == 1;
    }

    public static boolean isPassWord(String pwd){


        return false;
    }

}
