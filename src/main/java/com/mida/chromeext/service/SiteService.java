package com.mida.chromeext.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mida.chromeext.dao.SiteDAO;
import com.mida.chromeext.pojo.Site;
import com.mida.chromeext.pojo.SiteExample;
import com.mida.chromeext.utils.NumConst;
import com.mida.chromeext.vo.SiteAddVo;
import com.mida.chromeext.vo.SiteListQueryVo;


/**
 * @author lihaoyu
 * @date 2019/9/17 12:45
 */
@Service
public class SiteService {

    @Autowired
    private SiteDAO siteDAO;

    /**
     * 根据 sid 查询网站Po
     *
     * @param id 网站id
     * @return 网站Po 或者 null
     * @author lihaoyu
     * @date 2019/9/17 13:06
     */
    public Site getSiteById(Integer id) {
        Site site = siteDAO.selectByPrimaryKey(id);
        return site;
    }

    /**
     * 管理员添加网站
     *
     * @param SiteAddVos
     * @param adminId
     * @return List<Site>
     * @author lihaoyu
     * @date 2019/9/28 21:30 
     */
    public List<Site> addSites(List<SiteAddVo> SiteAddVos, Integer adminId) {

        // 1. 通过网站名确定不重复添加
        Set<String> titleSet = siteDAO.listAllTitle();
        for (SiteAddVo siteAddVo : SiteAddVos) {
            if(titleSet.contains(siteAddVo.getTitle())){
                return null;
            }
        }

        // 2. 插入 site 表
        List<Site> addSiteList = new ArrayList<>();
        Date date = new Date();
        for (SiteAddVo siteAddVo : SiteAddVos) {
            Site site = new Site();
            site.setCreatedAt(date);
            site.setUpdatedAt(date);
            site.setUsedCount(NumConst.NUM0);
            site.setCreatedBy(adminId);
            // 网站初始权重
            site.setWeight(50f);
        }




        return null;
    }



    /**
     * 当用户添加网站时，此网站的UsedCount要自增1
     *
     * @param sid 要增加的网站id
     * @return boolean 0表示sid不存在或自增失败，1表示自增成功
     * @author lihaoyu
     * @date 2019/9/19 14:58
     */
    public boolean increaseUsedCount(Integer sid) {
        int affectedRows = siteDAO.increaseUsedCount(sid);
        return affectedRows == NumConst.NUM1;
    }

    /**
     * 当用户删除网站时，此网站的UsedCount要自减1
     *
     * @param sid 要删除的网站id
     * @return boolean 0表示sid不存在或自减失败，1表示自减成功
     * @author lihaoyu
     * @date 2019/9/19 14:58
     */
    public boolean decreaseUsedCount(Integer sid) {
        int affectedRows = siteDAO.decreaseUsedCount(sid);
        return affectedRows == NumConst.NUM1;
    }

    /**
     * 批量网站的引用计数增1
     *
     * @param sidList
     * @return boolean 是否成功
     * @author lihaoyu
     * @date 2019/9/22 14:14
     */
    public boolean batchIncreaseUsedCount(List<Integer> sidList) {
        int affectedRows = siteDAO.batchIncreaseUsedCount(sidList);
        return affectedRows == sidList.size();
    }

    /**
     * 批量网站的引用计数减1
     *
     * @param sidList
     * @return boolean 是否成功
     * @author lihaoyu
     * @date 2019/9/22 14:14
     */
    public boolean batchDecreaseUsedCount(List<Integer> sidList) {
        int affectedRows = siteDAO.batchDecreaseUsedCount(sidList);
        return affectedRows == sidList.size();
    }

    /**
     * 判断网站是否都在数据库中
     *
     * @param sidList
     * @return 是否都存在
     * @author lihaoyu
     * @date 2019/9/22 14:22
     */
    public boolean isExist(List<Integer> sidList) {
        if (sidList == null || sidList.size() == NumConst.NUM0) {
            return false;
        }
        SiteExample example = new SiteExample();
        example.createCriteria().andSidIn(sidList);
        long count = siteDAO.countByExample(example);
        return count == sidList.size();
    }

    /**
     * 根据网站id删除
     *
     * @param sid 要删除的网站Id
     * @return boolean 0表示sid不存在或失败，1表示删除成功
     * @author lihaoyu
     * @date 2019/9/19 14:42
     */
    public boolean deleteSite(Integer sid) {
        int affectedRows = siteDAO.deleteByPrimaryKey(sid);
        return affectedRows == NumConst.NUM1;
    }



    public List<Site> listSitesByPage(SiteListQueryVo queryVo){
//        SiteExample example = new SiteExample();
//        SiteExample.Criteria criteria = example.createCriteria();
//        if(StringUtils.isNotBlank(queryVo.getKeyWord())){
//            criteria.andTitleLike("%"+queryVo.getKeyWord()+"%");
//        }
//        SiteCategory siteCategory = queryVo.getSiteCategory();
//        if(siteCategory != null && StringUtils.isNotBlank(siteCategory.getTitle())){
//            criteria.andCateIdEqualTo(siteCategory.getCid());
//        }
//        PageHelper.startPage(queryVo);
//        List<Site> sites = siteDAO.selectByExample(example);

        List<Site> sites = siteDAO.listSitesByPage(queryVo);
        return sites;
    }
}
