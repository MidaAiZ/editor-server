package com.mida.chromeext.service;

import com.mida.chromeext.dao.SiteDAO;
import com.mida.chromeext.dao.UserMenuDAO;
import com.mida.chromeext.pojo.UserMenu;
import com.mida.chromeext.pojo.UserMenuExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 用户添加的菜单列表
 */
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
     * 用户添加网站 校验网站是否存在，网站used_count自增1
     *
     * @param userId 用户Id
     * @param menu 用户菜单
     * @return userSite 用户网站关联po
     */
    @Transactional(rollbackFor = Exception.class)
    public UserMenu addOneUserMenu(Integer userId, UserMenu menu) {
        if (menu.getIsFolder()) { menu.setFolderId(0); }
        menu.setUserId(userId);
        menu.setCreatedAt(new Date());
        // siteService.increaseUsedCount(siteId);
        userMenuDAO.insert(menu);
        return menu;
    }

    /**
     * 用户批量添加网站 网站used_count自增1
     *
     * @param userId  用户id
     * @param menuList 用户菜单列表
     * @return boolean 是否成功
     * @author lihaoyu
     * @date 2019/9/22 12:57
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean addAndReplaceUserMenuList(Integer userId, List<UserMenu> menuList) {
        // 删除用户所有列表后重新生成
        UserMenuExample example = new UserMenuExample();
        example.createCriteria().andUserIdEqualTo(userId);
        userMenuDAO.deleteByExample(example);
        for (UserMenu menu : menuList) {
            if (menu.getIsFolder()) {
                menu.setFolderId(0);
            }
            menu.setUserId(userId);
        }
        // 生成新列表
        userMenuDAO.batchInsert(userId, menuList);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteUserMenus(Integer userId, List<Long> menuIdList) {
        userMenuDAO.batchDelete(userId, menuIdList);
        return true;
    }

}
