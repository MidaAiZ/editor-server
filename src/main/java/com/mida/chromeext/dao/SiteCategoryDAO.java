package com.mida.chromeext.dao;

import java.util.List;
import java.util.Set;

import com.mida.chromeext.mapper.SiteCategoryMapper;
import com.mida.chromeext.pojo.SiteCategory;

/**
 * @author lihaoyu
 * @date 2019/9/15 20:49
 */
public interface SiteCategoryDAO extends SiteCategoryMapper {

    /**
     *  查询所有site种类，屏蔽 site计数字段
     *
     * @return Po list
     * @author lihaoyu
     * @date 2019/9/28 15:12
     */
    List<SiteCategory>  listAllCategories();

    Set<String> listAllCategoriesTitle();

}
