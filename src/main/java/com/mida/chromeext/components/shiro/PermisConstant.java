package com.mida.chromeext.components.shiro;

public class PermisConstant {
    /**
     * 角色常量
     */
    // 超级管理员
    public static final String SUPER_ROLE = "super";
    // 管理员
    public static final String ADMIN_ROLE = "admin";

    /**
     * 管理员操作
     */
    // 添加管理员
    public static final String ADD_ADMIN = "sys:admins:add";
    // 修改管理员
    public static final String MODIFY_ADMIN = "sys:admins:modify";
    // 删除管理员
    public static final String DELETE_ADMIN = "sys:admins:delete";
    // 查看管理员信息
    public static final String SHOW_ADMIN = "sys:admins:show";
    // 添加管理员角色
    public static final String ADD_ROLE_TO_ADMIN = "sys:admins:add_roles";
    // 删除管理员角色
    public static final String REMOVE_ROLE_OFF_ADMIN = "sys:admins:remove_roles";

    /**
     * 角色权限操作
     */
    // 添加角色
    public static final String ADD_SYS_ROLE = "sys:roles:add";
    // 删除角色
    public static final String DELETE_SYS_ROLE = "sys:roles:delete";
    // 修改角色
    public static final String MODIFY_SYS_ROLE = "sys:roles:modify";
    // 查看角色
    public static final String SHOW_SYS_ROLE = "sys:roles:show";
    // 添加角色权限
    public static final String ADD_PERMIS_TO_ROLE = "sys:roles:add_permis";
    // 删除角色权限
    public static final String REMOVE_PERMIS_OFF_ROLE = "sys:roles:remove_permis";

    /**
     * 网站列表操作
     */
    // 添加网站
    public static final String ADD_SITE = "app:sites:add";
    // 修改网站
    public static final String MODIFY_SITE = "app:sites:modify";
    // 删除网站
    public static final String DELETE_SITE = "app:sites:delete";
    // 查看网站
    public static final String SHOW_SITE = "app:sites:show";

    /**
     * 网站历史记录操作
     */
    // 查看用户浏览历史记录
    public static final String SHOW_SITE_VIEW_HISTORY = "app:site_view_histories:show";
    // 用户浏览记录统计分析
    public static final String STATISTICS_SITE_VIEW_HISTORY = "app:site_view_histories:statistics";


    /**
     * 网站分类操作
     */
    // 查看用户浏览历史记录
    public static final String SHOW_SITE_CATEGORY = "app:site_categories:show";
    // 添加网站分类
    public static final String ADD_SITE_CATEGORY = "app:site_categories:add";
    // 修改网站分类
    public static final String MODIFY_SITE_CATEGORY = "app:site_categories:modify";
    // 删除网站分类
    public static final String DELETE_SITE_CATEGORY = "app:site_categories:delete";


    /**
     * 搜索引擎配置操作
     */
    // 查看搜索引擎配置
    public static final String SHOW_SEARCH_ENGINE = "app:search_engines:show";
    // 添加搜索引擎
    public static final String ADD_SEARCH_ENGINE = "app:search_engines:add";
    // 修改搜索引擎
    public static final String MODIFY_SEARCH_ENGINE = "app:search_engines:modify";
    // 删除搜索引擎
    public static final String DELETE_SEARCH_ENGINE = "app:search_engines:delete";

    /**
     * 壁纸操作
     */
    // 添加壁纸
    public static final String ADD_BG_PICTURE = "app:bg_pictures:add";
    // 修改壁纸
    public static final String MODIFY_BG_PICTURE = "app:bg_pictures:modify";
    // 删除壁纸
    public static final String DELETE_BG_PICTURE = "app:bg_pictures:delete";
    // 查看壁纸
    public static final String SHOW_BG_PICTURE = "app:bg_pictures:show";

    /**
     * 默认菜单操作
     */
    /**
     * 壁纸操作
     */
    // 添加壁纸
    public static final String ADD_DEFAULT_MENU = "app:default_menus:add";
    // 修改壁纸
    public static final String MODIFY_DEFAULT_MENU = "app:default_menus:modify";
    // 删除壁纸
    public static final String DELETE_DEFAULT_MENU = "app:default_menus:delete";
    // 查看壁纸
    public static final String SHOW_DEFAULT_MENU = "app:default_menus:show";


    /**
     * 用户操作
     */
    // 查看用户
    public static final String SHOW_USER = "app:users:show";
    // 修改用户
    public static final String MODIFY_USER = "app:users:modify";
    // 用户数据统计
    public static final String STATISTICS_USER = "app:users:statistics";

}

