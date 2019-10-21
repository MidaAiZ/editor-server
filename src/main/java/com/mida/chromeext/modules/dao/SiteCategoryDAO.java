package com.mida.chromeext.modules.dao;

import com.mida.chromeext.modules.dao.mapper.SiteCategoryMapper;
import com.mida.chromeext.modules.pojo.SiteCategory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author lihaoyu
 * @date 2019/9/15 20:49
 */
@Repository
public interface SiteCategoryDAO extends SiteCategoryMapper {

    /**
     * 查询所有site种类，屏蔽 site计数字段
     *
     * @return Po list
     * @author lihaoyu
     * @date 2019/9/28 15:12
     */
    List<SiteCategory> listAllCategories();

    Set<String> listAllCategoriesTitle();

    int batchDelete(List<Integer> cateIds);
}
