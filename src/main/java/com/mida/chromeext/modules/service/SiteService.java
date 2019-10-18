package com.mida.chromeext.modules.service;

import com.github.pagehelper.PageHelper;
import com.mida.chromeext.modules.dao.CountriesSiteDAO;
import com.mida.chromeext.modules.dao.SiteDAO;
import com.mida.chromeext.modules.pojo.CountriesSite;
import com.mida.chromeext.modules.pojo.Site;
import com.mida.chromeext.modules.pojo.SiteExample;
import com.mida.chromeext.utils.NumConst;
import com.mida.chromeext.modules.vo.SiteAddVo;
import com.mida.chromeext.modules.vo.SiteListQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/17 12:45
 */
@Service
public class SiteService {

    @Autowired
    private SiteDAO siteDAO;

    @Autowired
    private CountriesSiteDAO countriesSiteDAO;

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
     * 管理员添加网站   同时添加 site 和 country 关联
     *
     * @param SiteAddVos
     * @param adminId
     * @return List<Site>
     * @author lihaoyu
     * @date 2019/9/28 21:30
     */
    public List<Site> addSites(List<SiteAddVo> SiteAddVos, Integer adminId) {
        List<Site> addSiteList = new ArrayList<>();
        Date date = new Date();
        for (SiteAddVo siteAddVo : SiteAddVos) {
            Site site = Site.builder().createdBy(adminId).weight(50f).createdAt(date).updatedAt(date)
                    .icon(siteAddVo.getIcon()).title(siteAddVo.getTitle()).cateId(siteAddVo.getCateId())
                    .url(siteAddVo.getUrl()).build();
            siteDAO.insertSelective(site);
            addSiteList.add(site);

            // 添加国家网站关联表
            List<String> countryCodeList = siteAddVo.getCountryCodes();
            for (String countryCode : countryCodeList) {
                CountriesSite countriesSite = new CountriesSite();
                countriesSite.setSiteId(site.getSid());
                countriesSite.setCountryCode(countryCode);
                countriesSiteDAO.insert(countriesSite);
            }
        }
        return addSiteList;
    }


    /**
     * 更新一条网站数据
     * @param site
     * @return
     */
    public Boolean update(Site site) {
        return siteDAO.updateByPrimaryKeySelective(site) > 0;
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

    /**
     * 查询网站列表
     * @param queryVo
     * @return
     */
    public List<Site> queryList(SiteListQueryVo queryVo) {
        PageHelper.startPage(queryVo);
        List<Site> sites = siteDAO.queryList(queryVo);
        return sites;
    }

    /**
     * 查询网站列表，获取关联对象
     * @param queryVo
     * @return
     */
    public List<Site> queryListWithRelations(SiteListQueryVo queryVo) {
        PageHelper.startPage(queryVo);
        List<Site> sites = siteDAO.queryListWithRelations(queryVo);
        return sites;
    }

}
