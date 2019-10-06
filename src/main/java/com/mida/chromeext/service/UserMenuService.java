package com.mida.chromeext.service;

import com.mida.chromeext.dao.SiteDAO;
import com.mida.chromeext.dao.UserMenuDAO;
import com.mida.chromeext.pojo.UserMenu;
import com.mida.chromeext.pojo.UserMenuExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用户添加的菜单列表
 */
@Service
public class UserMenuService {
    @Autowired
    private UserMenuDAO userMenuDAO;
    @Autowired
    private SiteDAO siteDAO;

    /**
     * 查询用户所添加的网站集合 未排序未分页
     *
     * @param userId 用户id
     * @return UserMenu集合 用户所添加的菜单列表
     */
    public List<UserMenu> getMenuListByUserId(Integer userId) {
        return userMenuDAO.listMenuByUserId(userId);
    }

    /**
     * 用户添加网站 校验网站是否存在
     *
     * @param userId 用户Id
     * @param menu 用户菜单
     * @return userMenu 用户菜单
     */
    @Transactional(rollbackFor = Exception.class)
    public UserMenu addOneUserMenu(Integer userId, UserMenu menu) {
        if (menu.getIsFolder() != null && menu.getIsFolder()) { menu.setFolderId(0); }
        menu.setUserId(userId);
        menu.setCreatedAt(new Date());
        menu.setMid(UUID.randomUUID().toString().replaceAll("-", ""));
        userMenuDAO.insertWithUUID(menu);
        return menu;
    }

    /**
     * 用户批量添加网站
     *
     * @param userId  用户id
     * @param menuList 用户菜单列表
     * @return boolean 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public int addAndReplaceUserMenuList(Integer userId, List<UserMenu> menuList) {
        // 删除用户所有列表后重新生成
        UserMenuExample example = new UserMenuExample();
        example.createCriteria().andUserIdEqualTo(userId);
        userMenuDAO.deleteByExample(example);
        for (UserMenu menu : menuList) {
            if (menu.getIsFolder() != null && menu.getIsFolder()) {
                menu.setFolderId(0);
            }
            menu.setUserId(userId);
            menu.setMid(UUID.randomUUID().toString().replaceAll("-", ""));
        }
        // 生成新列表
        return userMenuDAO.batchInsert(userId, menuList);
    }

    /**
     * 更新一个用户菜单
     *
     * @param userId 用户Id
     * @param menu 用户菜单
     * @return int
     */
    public int updateOneMenu(Integer userId, UserMenu menu) {
        UserMenuExample example = new UserMenuExample();
        // 禁止更新用户id
        menu.setUserId(null);
        // 文件夹类型禁止内嵌文件夹
        if (menu.getIsFolder() != null && menu.getIsFolder()) {
            menu.setFolderId(0);
        }
        example.createCriteria().andUserIdEqualTo(userId).andMidEqualTo(menu.getMid());
        return userMenuDAO.updateByExampleSelective(menu, example);
    }

    /**
     * 批量更新用户菜单
     *
     * @param userId 用户Id
     * @param menuList 用户菜单列表
     * @return int
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateMenuList(Integer userId, List<UserMenu> menuList) {
        int size = 0;
        for (UserMenu menu : menuList) {
            size += updateOneMenu(userId, menu);
        }
        return size;
    }


    public int deleteUserMenus(Integer userId, List<Long> menuIdList) {
        return userMenuDAO.batchDelete(userId, menuIdList);
    }
}
