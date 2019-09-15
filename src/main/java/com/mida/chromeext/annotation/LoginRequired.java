package com.mida.chromeext.annotation;

import java.lang.annotation.*;

/**
 * 类Login的功能描述:
 * 登录效验
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginRequired {
}
