package com.mida.chromeext.dao;

import com.mida.chromeext.mapper.UserMenuMapper;
import com.mida.chromeext.pojo.UserMenu;

import java.util.List;

public interface UserMenuDAO extends UserMenuMapper {
    /**
     * 展示用户添加的菜单
     *
     * @param userId 用户id
     * @return Menu Po
     */
    List<UserMenu> listMenuByUserId(Integer userId);

    /**
     * 用户批量添加菜单
     *
     * @param userId
     * @param menuList
     * @return affectedRows
     */
    int batchInsert(Integer userId, List<UserMenu> menuList);

    /**
     * 用户批量删除菜单
     *
     * @param userId
     * @param menuIdList
     * @return affectedRows
     */
    int batchDelete(Integer userId, List<Long> menuIdList);
}
