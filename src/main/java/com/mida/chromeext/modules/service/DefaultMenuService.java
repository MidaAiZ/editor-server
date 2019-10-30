package com.mida.chromeext.modules.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.mida.chromeext.modules.dao.DefaultMenuDAO;
import com.mida.chromeext.modules.dto.UserMenuItemDto;
import com.mida.chromeext.modules.pojo.DefaultMenu;
import com.mida.chromeext.modules.pojo.DefaultMenuExample;
import com.mida.chromeext.modules.pojo.UserMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultMenuService {
    @Autowired
    private DefaultMenuDAO defaultMenuDAO;

    /**
     * 获取默认菜单配置列表
     *
     * @return List<DefaultMenu>
     */
    public List<DefaultMenu> getAllList() {
        DefaultMenuExample example = new DefaultMenuExample();
        example.setOrderByClause("did desc");
        return defaultMenuDAO.selectByExample(example);
    }

    /**
     * 根据国家码获取默认菜单配置
     *
     * @param countryCode
     * @return DefaultMenu
     */
    public DefaultMenu getOneByCountryCode(String countryCode) {
        return defaultMenuDAO.selectByCountryCode(countryCode);
    }


    /**
     * 创建一个默认菜单配置
     *
     * @param menu
     * @return Boolean
     */
    public boolean create(DefaultMenu menu) {
        // 过滤内容
        menu.setMenus(JSONObject.toJSONString(JSONObject.parseArray(menu.getMenus(), UserMenuItemDto.class)));
        return defaultMenuDAO.insertSelective(menu) > 0;
    }

    /**
     * 更新一个默认菜单配置
     *
     * @param menu
     * @return Boolean
     */
    public boolean update(DefaultMenu menu) {
        menu.setMenus(JSONObject.toJSONString(JSONObject.parseArray(menu.getMenus(), UserMenuItemDto.class)));
        return defaultMenuDAO.updateByPrimaryKeySelective(menu) > 0;
    }

    /**
     * 删除吧一个默认菜单配置
     *
     * @param did
     * @return Boolean
     */
    public boolean delete(int did) {
        return defaultMenuDAO.deleteByPrimaryKey(did) > 0;
    }

    /**
     * 批量创建默认菜单配置
     *
     * @param menuList
     * @return count
     */
    @Transactional(rollbackFor = Exception.class)
    public int createList(List<DefaultMenu> menuList) {
        int count = 0;
        for (DefaultMenu menu : menuList) {
            if (create(menu)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 批量创建默认菜单配置
     *
     * @param menuList
     * @return count
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateList(List<DefaultMenu> menuList) {
        int count = 0;
        for (DefaultMenu menu : menuList) {
            if (update(menu)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 批量删除默认菜单配置
     *
     * @param idList
     * @return count
     */
    @Transactional(rollbackFor = Exception.class)
    public int deleteList(List<Integer> idList) {
        int count = 0;
        for (Integer id : idList) {
            if (delete(id)) {
                count++;
            }
        }
        return count;
    }
}
