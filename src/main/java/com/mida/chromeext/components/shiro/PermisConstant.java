package com.mida.chromeext.components.shiro;

import com.mida.chromeext.annotation.SysPermission;

/**
 * 权限常量
 */
public class PermisConstant {
    /**
     * 管理员操作
     */
    @SysPermission(desc="添加管理员", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String ADD_ADMIN = "sys:admins:add";
    @SysPermission(desc="修改管理员", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String MODIFY_ADMIN = "sys:admins:modify";
    @SysPermission(desc="删除管理员", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String DELETE_ADMIN = "sys:admins:delete";
    @SysPermission(desc = "查看管理员基本信息", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String SHOW_ADMIN = "sys:admins:show";
    @SysPermission(desc = "给管理员添加角色", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String ADD_ROLE_TO_ADMIN = "sys:admins:add_roles";
    @SysPermission(desc = "移除管理员的角色", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String REMOVE_ROLE_OFF_ADMIN = "sys:admins:remove_roles";

    /**
     * 角色权限操作
     */
    @SysPermission(desc = "添加系统角色", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String ADD_SYS_ROLE = "sys:roles:add";
    @SysPermission(desc = "删除系统角色", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String DELETE_SYS_ROLE = "sys:roles:delete";
    @SysPermission(desc = "修改系统角色", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String MODIFY_SYS_ROLE = "sys:roles:modify";
    @SysPermission(desc = "查看系统角色", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String SHOW_SYS_ROLE = "sys:roles:show";
    @SysPermission(desc = "给系统角色添加权限", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String ADD_PERMIS_TO_ROLE = "sys:roles:add_permis";
    @SysPermission(desc = "移出系统角色的权限", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String REMOVE_PERMIS_OFF_ROLE = "sys:roles:remove_permis";

    /**
     * 网站列表操作
     */
    @SysPermission(desc = "添加网站信息", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String ADD_SITE = "app:sites:add";
    @SysPermission(desc = "修改网站信息", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String MODIFY_SITE = "app:sites:modify";
    @SysPermission(desc = "删除网站", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String DELETE_SITE = "app:sites:delete";
    @SysPermission(desc = "查看网站信息", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String SHOW_SITE = "app:sites:show";
    @SysPermission(desc = "统计网站信息", roles = {RoleConstant.SUPER_ROLE})
    public static final String STATISTICS_SITE = "app:sites:statistics";

    /**
     * 网站历史记录操作
     */
    @SysPermission(desc = "查看用户网站浏览信息", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String SHOW_SITE_VIEW_HISTORY = "app:site_view_histories:show";
    @SysPermission(desc = "统计分析用户网站浏览信息", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String STATISTICS_SITE_VIEW_HISTORY = "app:site_view_histories:statistics";


    /**
     * 网站分类操作
     */
    @SysPermission(desc = "查看网站分类", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String SHOW_SITE_CATEGORY = "app:site_categories:show";
    @SysPermission(desc = "添加网站分类", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String ADD_SITE_CATEGORY = "app:site_categories:add";
    @SysPermission(desc = "修改网站分类", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String MODIFY_SITE_CATEGORY = "app:site_categories:modify";
    @SysPermission(desc = "删除网站分类", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String DELETE_SITE_CATEGORY = "app:site_categories:delete";


    /**
     * 搜索引擎配置操作
     */
    @SysPermission(desc = "查看不同国家的默认搜索引擎配置", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String SHOW_SEARCH_ENGINE = "app:search_engines:show";
    @SysPermission(desc = "添加不同国家的默认搜索引擎配置", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String ADD_SEARCH_ENGINE = "app:search_engines:add";
    @SysPermission(desc = "修改不同国家的默认搜索引擎配置", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String MODIFY_SEARCH_ENGINE = "app:search_engines:modify";
    @SysPermission(desc = "删除不同国家的默认搜索引擎配置", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String DELETE_SEARCH_ENGINE = "app:search_engines:delete";

    /**
     * 壁纸操作
     */
    @SysPermission(desc = "添加壁纸库", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String ADD_BG_PICTURE = "app:bg_pictures:add";
    @SysPermission(desc = "修改壁纸库", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String MODIFY_BG_PICTURE = "app:bg_pictures:modify";
    @SysPermission(desc = "删除壁纸库", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String DELETE_BG_PICTURE = "app:bg_pictures:delete";
    @SysPermission(desc = "查看壁纸库", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String SHOW_BG_PICTURE = "app:bg_pictures:show";

    /**
     * 默认菜单操作
     */
    @SysPermission(desc = "添加不同国家的默认菜单列表配置", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String ADD_DEFAULT_MENU = "app:default_menus:add";
    @SysPermission(desc = "修改不同国家的默认菜单列表配置", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String MODIFY_DEFAULT_MENU = "app:default_menus:modify";
    @SysPermission(desc = "删除不同国家的默认菜单列表配置", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String DELETE_DEFAULT_MENU = "app:default_menus:delete";
    @SysPermission(desc = "查看不同国家的默认菜单列表配置", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String SHOW_DEFAULT_MENU = "app:default_menus:show";


    /**
     * 用户操作
     */
    @SysPermission(desc = "查看用户信息", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String SHOW_USER = "app:users:show";
    @SysPermission(desc = "修改用户信息", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String MODIFY_USER = "app:users:modify";
    @SysPermission(desc = "用户数据统计分析", roles = {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String STATISTICS_USER = "app:users:statistics";

    /**
     * 国家地区操作
     */
    @SysPermission(desc = "添加国家(地区)", roles =  {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String ADD_COUNTRY = "sys:countries:add";
    @SysPermission(desc = "更新国家(地区)", roles =  {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String MODIFY_COUNTRY = "sys:countries:modify";
    @SysPermission(desc = "删除国家(地区)", roles =  {RoleConstant.SUPER_ROLE, RoleConstant.ADMIN_ROLE})
    public static final String DELETE_COUNTRY = "sys:countries:delete";
}

