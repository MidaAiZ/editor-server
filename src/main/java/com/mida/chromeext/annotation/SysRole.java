package com.mida.chromeext.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解用于定义系统角色常量
 * 在系统初始化角色数据表时可以通过该注解获取角色信息并注入数据库
 */
@Target(ElementType.FIELD)
// 运行时有效
@Retention(RetentionPolicy.RUNTIME)
public @interface SysRole {
    /**
     * 角色描述字段
     */
    String desc() default "";

    /**
     * 角色包含的权限列表
     */
    String[] includePermissions() default {};
}
