package net.tabplus.api.modules.service;

import com.alibaba.fastjson.JSONObject;
import net.tabplus.api.modules.dao.UserMenuDAO;
import net.tabplus.api.modules.dao.SiteDAO;
import net.tabplus.api.modules.dto.UserMenuItemDto;
import net.tabplus.api.modules.pojo.UserMenu;
import net.tabplus.api.modules.pojo.UserMenuExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public UserMenu getMenuItemsByUserId(Integer userId) {
        return userMenuDAO.getMenuItemsByUserId(userId);
    }

    public Boolean create(Integer userId, UserMenu menu) {
        menu.setUserId(userId);
        menu.setCreatedAt(new Date());
        menu.setUpdatedAt(new Date());
        return userMenuDAO.insertSelective(menu) > 0;
    }

    /**
     * 更新用户菜单
     *
     * @param userId    用户Id
     * @param menuPages 用户菜单
     * @return String 更新日期
     */
    public UserMenu update(Integer userId, List<List<UserMenuItemDto>> menuPages) {
        UserMenuExample example = new UserMenuExample();
        UserMenu menu = new UserMenu();
        List<Integer> siteIds = new ArrayList<>();
        for (List<UserMenuItemDto> page : menuPages) {
            for (UserMenuItemDto item : page) {
                System.out.println(item);
//                // 设置siteIds快照
                if (item.getSiteId() != null && item.getSiteId() > 0) {
                    siteIds.add(item.getSiteId());
                }
                // 禁止文件夹嵌套
                if (item.getIsFolder() && item.getChildren() != null) {
                    for (UserMenuItemDto son : item.getChildren()) {
                        son.setIsFolder(false);
                        son.setChildren(null);
                    }
                }
            }
        }
        menu.setSiteIds(JSONObject.toJSONString(siteIds));
        menu.setMenus(JSONObject.toJSONString(menuPages));
        example.createCriteria().andUserIdEqualTo(userId);
        if (getMenuItemsByUserId(userId) == null) {
            return create(userId, menu) ? menu : null;
        }
        menu.setUpdatedAt(new Date());
        return userMenuDAO.updateByExampleSelective(menu, example) > 0 ? menu : null;
    }
}
