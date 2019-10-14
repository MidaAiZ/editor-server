package com.mida.chromeext.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解用于定义系统权限常量
 * 在系统初始化权限数据表时可以通过该注解获取权限信息并注入数据库
 */
@Target(ElementType.FIELD)
// 运行时有效
@Retention(RetentionPolicy.RUNTIME)
public @interface SysPermission {
    /**
     * 权限描述字段
     */
    String desc() default "";

    /**
     * 权限类型
     */
    String type() default "permission";

    /**
     * 权限路径, 预留字段
     */
    String url() default "manage";

    /**
     * 默认属于的系统角色列表
     */
    String[] roles() default {};
}
