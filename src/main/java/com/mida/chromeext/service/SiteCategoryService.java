package com.mida.chromeext.service;

import com.mida.chromeext.dao.SiteCategoryDAO;
import com.mida.chromeext.pojo.SiteCategory;
import com.mida.chromeext.pojo.SiteCategoryExample;
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
        categories.forEach(s -> siteCategoryDAO.insert(s));
        return categories;
    }

    public int batchDelete(List<Integer> Ids) {
        SiteCategoryExample example = new SiteCategoryExample();
        example.createCriteria().andCidIn(Ids);
        int affectedRows = siteCategoryDAO.deleteByExample(example);
        return affectedRows;
    }

    public SiteCategory updateCategories(SiteCategory categories) {
        categories.setUpdatedAt(new Date());
        siteCategoryDAO.updateByPrimaryKey(categories);
        return categories;
    }
}
