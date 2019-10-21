package com.mida.chromeext.modules.service;

import com.google.common.collect.Lists;
import com.mida.chromeext.modules.dao.SiteCategoryDAO;
import com.mida.chromeext.modules.pojo.Site;
import com.mida.chromeext.modules.pojo.SiteCategory;
import com.mida.chromeext.modules.pojo.SiteCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * @author lihaoyu
 * @date 2019年9月28日16:32:41
 */
@Service
public class SiteCategoryService {

    @Autowired
    private SiteCategoryDAO siteCategoryDAO;

    @Autowired
    private SiteService siteService;

    /**
     * 获取网站分类列表，屏蔽敏感字段
     *
     * @return SiteCategory List
     * @author lihaoyu
     * @date 2019/9/28 16:33
     */
    public List<SiteCategory> listAllCategories() {
        return siteCategoryDAO.listAllCategories();
    }

    /**
     * 根据cateId获取一条记录
     *
     * @param id
     * @return
     */
    public SiteCategory getCateById(Integer id) {
        return siteCategoryDAO.selectByPrimaryKey(id);
    }

    /**
     * 添加种类，若已有Title重复，返回null
     *
     * @param categories List<SiteCategory>
     * @return List<SiteCategory> maybe null
     * @author lihaoyu
     * @date 2019/9/28 16:47
     */
    @Transactional(rollbackFor = Exception.class)
    public List<SiteCategory> addCategories(List<SiteCategory> categories) {
        Set<String> titles = siteCategoryDAO.listAllCategoriesTitle();
        for (SiteCategory category : categories) {
            if (titles.contains(category.getTitle())) {
                return null;
            }
            Date date = new Date();
            category.setCreatedAt(date);
            category.setSitesCount(0);
            category.setUpdatedAt(date);
        }
        // 少量批添加
        categories.forEach(s -> siteCategoryDAO.insertSelective(s));
        return categories;
    }

    /**
     * 批量删除分类
     * 仅能删除没有关联网站的分类
     * @param ids
     * @return
     */
    public int batchDelete(List<Integer> ids) {
        return siteCategoryDAO.batchDelete(ids);
    }

    /**
     * 删除一个分类
     * 仅能删除没有关联网站的记录
     * @param id
     * @return
     */
    public boolean deleteById(int id) {
        return siteCategoryDAO.batchDelete(Lists.newArrayList(id)) > 0;
    }

    /**
     * 批量更新站点分类
     *
     * @param categories
     * @return
     */
    public List<SiteCategory> updateCategories(List<SiteCategory> categories) {
        for (SiteCategory category : categories) {
            updateOneCategory(category);
        }
        return categories;
    }

    /**
     * 更新1个站点分类
     */
    public Boolean updateOneCategory(SiteCategory category) {
        category.setSitesCount(null);
        category.setCreatedAt(null);
        category.setUpdatedAt(new Date());
        return siteCategoryDAO.updateByPrimaryKeySelective(category) > 0;
    }

}
