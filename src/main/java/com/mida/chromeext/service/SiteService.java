package com.mida.chromeext.service;

import com.mida.chromeext.utils.NumConst;
import org.springframework.stereotype.Service;

import com.mida.chromeext.dao.SiteDAO;
import com.mida.chromeext.pojo.Site;

import java.util.Date;

/**
 * @author lihaoyu
 * @date 2019/9/17 12:45
 */
@Service
public class SiteService {

    private SiteDAO siteDAO;


    /**
     * 根据 sid 查询网站Po
     *
     * @param id 网站id
     * @return   网站Po 或者 null
     * @author lihaoyu
     * @date 2019/9/17 13:06
     */
    public Site getSiteById(Integer id){
        Site site = siteDAO.selectByPrimaryKey(id);
        return site;
    }

    /**
     * 管理员添加网站 未做校验
     *
     * @param site 被添加的site
     * @param adminId 添加者id
     * @return site 添加的网站po
     * @author lihaoyu
     * @date 2019/9/19 14:36
     */
    public Site addSite(Site site, Integer adminId){
        Date date = new Date();
        site.setCreatedAt(date);
        site.setUpdatedAt(date);
        site.setUsedCount(NumConst.NUM0);
        site.setCreatedBy(adminId);
        // 网站初始权重
        site.setWeight(50f);
        int siteId = siteDAO.insertSelective(site);
        site.setSid(siteId);
        return site;
    }

    /**
     * 当用户添加网站时，此网站的UsedCount要自增1
     *
     * @param sid 要增加的网站id
     * @return boolean 0表示sid不存在或自增失败，1表示自增成功
     * @author lihaoyu
     * @date 2019/9/19 14:58
     */
    public boolean increaseUsedCount(Integer sid){
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
    public boolean decreaseUsedCount(Integer sid){
        int affectedRows = siteDAO.decreaseUsedCount(sid);
        return affectedRows == NumConst.NUM1;
    }


    /**
     * 根据网站id删除
     *
     * @param sid 要删除的网站Id
     * @return boolean 0表示sid不存在或失败，1表示删除成功
     * @author lihaoyu
     * @date 2019/9/19 14:42
     */
    public boolean deleteSite(Integer sid){
        int affectedRows = siteDAO.deleteByPrimaryKey(sid);
        return affectedRows == NumConst.NUM1;
    }





}
