package net.tabplus.api.components.shiro;

import net.tabplus.api.annotation.SysRole;

/**
 * 角色常量
 */
public class RoleConstant {
    @SysRole(desc = "超级管理员爸爸，很厉害的那种")
    public static final String SUPER_ROLE = "super";
    @SysRole(desc = "系统管理员")
    public static final String ADMIN_ROLE = "admin";
}
