package com.mida.chromeext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mida.chromeext.dao.SiteDAO;
import com.mida.chromeext.dao.UserSiteDAO;
import com.mida.chromeext.pojo.Site;
import com.mida.chromeext.pojo.UserSite;
import com.mida.chromeext.pojo.UserSiteExample;
import com.mida.chromeext.utils.NumConst;

/**
 * 用户网站关联服务
 * @author lihaoyu
 * @date 2019/9/17 12:26
 */
@Service
public class UserSiteService {

    @Autowired
    private UserSiteDAO userSiteDAO;
    @Autowired
    private SiteDAO siteDAO;
    @Autowired
    private SiteService siteService;

    /**
     * 查询用户所添加的网站集合 未排序未分页
     *
     * @param userId 用户id
     * @return Site集合 用户所添加的
     * @author lihaoyu
     * @date 2019/9/19 16:31
     */
    public List<Site> listSiteByUserId(Integer userId){
        return userSiteDAO.listSiteByUserId(userId);
    }


    /**
     * 用户添加网站 校验网站是否存在，网站used_count自增1
     *
     * @param userId 用户Id
     * @param siteId 网站Id
     * @return userSite 用户网站关联po
     * @author lihaoyu
     * @date 2019/9/19 15:06
     */
    @Transactional(rollbackFor = Exception.class)
    public UserSite addSiteByUserIdAndSiteId(Integer userId, Integer siteId){
        Site site = siteDAO.selectByPrimaryKey(siteId);
        if(site == null){
            return null;
        }
        UserSite userSite = new UserSite();
        userSite.setSiteId(siteId);
        userSite.setUserId(userId);
        userSiteDAO.insert(userSite);
        // 网站被引用计数加一
        siteService.increaseUsedCount(siteId);
        return userSite;
    }

    /**
     * 用户批量添加网站 网站used_count自增1
     *
     * @param userId 用户id
     * @param siteIds 网站ids
     * @return boolean 是否成功
     * @author lihaoyu
     * @date 2019/9/22 12:57
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean addSitesByUserIdAndSiteIds(Integer userId, List<Integer> siteIds){
        boolean exist = siteService.isExist(siteIds);
        // 被添加的网站有不存在于数据库中的
        if(!exist){
            return false;
        }
        userSiteDAO.batchInsert(userId, siteIds);
        // 批量网站被引用数加一
        siteService.batchIncreaseUsedCount(siteIds);
        return true;
    }

    /**
     * 用户删除网站 网站存在时，网站used_count自减1
     *
     * @param userId 用户Id
     * @param siteId 网站Id
     * @return boolean 是否成功删除
     * @author lihaoyu
     * @date 2019/9/19 16:41 
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteSiteByUserIdAndSiteId(Integer userId, Integer siteId){
        UserSite userSite = new UserSite();
        userSite.setSiteId(siteId);
        userSite.setUserId(userId);
        UserSiteExample example = new UserSiteExample();
        example.createCriteria().andSiteIdEqualTo(siteId).andUserIdEqualTo(userId);
        int affectedRows = userSiteDAO.deleteByExample(example);
        // 网站被引用计数减一
        if(affectedRows != NumConst.NUM0){
            siteService.decreaseUsedCount(siteId);
        }
        return affectedRows == NumConst.NUM1;
    }

    /**
     * 用户批量删除网站 网站used_count自减1
     *
     * @param userId 用户id
     * @param siteIds 网站ids
     * @return boolean 是否成功
     * @author lihaoyu
     * @date 2019/9/22 12:57
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteSitesByUserIdAndSiteIds(Integer userId, List<Integer> siteIds){
        boolean exist = siteService.isExist(siteIds);
        // 被删除的网站有不存在于数据库中的
        if(!exist){
            return false;
        }
        userSiteDAO.batchDelete(userId, siteIds);
        // 批量网站被引用数减一
        siteService.batchDecreaseUsedCount(siteIds);

        return true;
    }

}
