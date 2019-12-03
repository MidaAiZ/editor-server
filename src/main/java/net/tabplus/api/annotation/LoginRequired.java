package net.tabplus.api.annotation;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.lang.annotation.*;

/**
 * 类Login的功能描述:
 * 登录效验
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginRequired {
    /**
     * 是否强制要求登录，强制时未登录就抛出异常
     * @return
     */
    boolean force() default true;
}
