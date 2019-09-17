package com.mida.chromeext.service;

import org.springframework.stereotype.Service;

import com.mida.chromeext.dao.SiteDAO;
import com.mida.chromeext.pojo.Site;

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




}
