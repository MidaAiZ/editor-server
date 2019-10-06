package com.mida.chromeext.service;

import com.github.pagehelper.PageHelper;
import com.mida.chromeext.dao.DefaultMenuDAO;
import com.mida.chromeext.pojo.DefaultMenu;
import com.mida.chromeext.pojo.DefaultMenuExample;
import com.mida.chromeext.vo.ListQueryVo;
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
     * @param queryVo
     * @return List<DefaultMenu>
     */
    public List<DefaultMenu> getList(ListQueryVo queryVo) {
        PageHelper.startPage(queryVo);
        DefaultMenuExample example = new DefaultMenuExample();
        example.setOrderByClause("did desc");
        return defaultMenuDAO.selectByExample(example);
    }

    /**
     * 根据国家码获取默认菜单配置
     * @param countryCode
     * @return DefaultMenu
     */
    public DefaultMenu getOneByCountryCode(String countryCode) {
        return defaultMenuDAO.selectByCountryCode(countryCode);
    }

    /**
     * 获取默认的默认菜单配置
     * @return DefaultMenu
     */
    public DefaultMenu getDefaultMenu() {
        DefaultMenu menu = defaultMenuDAO.selectDefaultMenu();
        if (menu == null) {

            List<DefaultMenu> menuList = getList(new ListQueryVo(1, 1));
            menu = menuList.size() > 0 ? menuList.get(0) : null;
        }
        return menu;
    }

    /**
     * 设置系统默认的默认菜单列表配置
     * @param did
     * @param isDefault
     * @return Boolean
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean setDefault(Integer did, boolean isDefault) {
        DefaultMenuExample example = new DefaultMenuExample();
        DefaultMenu menu = new DefaultMenu();
        menu.setIsDefault(false);
        defaultMenuDAO.updateByExampleSelective(menu, example);
        menu.setIsDefault(isDefault);
        menu.setDid(did);
        return defaultMenuDAO.updateByPrimaryKeySelective(menu) > 0;
    }

    /**
     * 创建一个默认菜单配置
     * @param menu
     * @return Boolean
     */
    public boolean create(DefaultMenu menu) {
        return defaultMenuDAO.insert(menu) > 0;
    }

    /**
     * 更新一个默认菜单配置
     * @param menu
     * @return Boolean
     */
    public boolean update(DefaultMenu menu) {
        return defaultMenuDAO.updateByPrimaryKeySelective(menu) > 0;
    }

    /**
     * 删除吧一个默认菜单配置
     * @param did
     * @return Boolean
     */
    public boolean delete(int did) {
        return defaultMenuDAO.deleteByPrimaryKey(did) > 0;
    }

    /**
     * 批量创建默认菜单配置
     * @param menuList
     * @return count
     */
    @Transactional(rollbackFor = Exception.class)
    public int createList(List<DefaultMenu> menuList) {
        int count = 0;
        for (DefaultMenu menu : menuList) {
            if (create(menu)) { count++; }
        }
        return count;
    }

    /**
     * 批量创建默认菜单配置
     * @param menuList
     * @return count
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateList(List<DefaultMenu> menuList) {
        int count = 0;
        for (DefaultMenu menu : menuList) {
            if (update(menu)) { count++; }
        }
        return count;
    }

    /**
     * 批量删除默认菜单配置
     * @param idList
     * @return count
     */
    @Transactional(rollbackFor = Exception.class)
    public int deleteList(List<Integer> idList) {
        int count = 0;
        for (Integer id : idList) {
            if (delete(id)) { count++; }
        }
        return count;
    }
}
