package net.tabplus.api.modules.dao;

import net.tabplus.api.modules.dao.mapper.UserMenuMapper;
import net.tabplus.api.modules.pojo.UserMenu;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMenuDAO extends UserMenuMapper {
    /**
     * 展示用户添加的菜单
     *
     * @param userId 用户id
     * @return Menu Po
     */
    UserMenu getMenuItemsByUserId(Integer userId);
}
