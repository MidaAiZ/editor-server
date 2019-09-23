package com.mida.chromeext.dao;

import com.mida.chromeext.mapper.UserSiteMapper;
import com.mida.chromeext.pojo.Site;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/15 20:50
 */
public interface UserSiteDAO extends UserSiteMapper {

    /**
     * 展示用户添加的网站
     *
     * @param userId 用户id
     * @return Site Po
     * @author lihaoyu
     * @date 2019/9/22 13:58
     */
    List<Site> listSiteByUserId(Integer userId);

    /**
     * 用户批量添加网站
     *
     * @param userId
     * @param siteIdList
     * @return affectedRows
     * @author lihaoyu
     * @date 2019/9/22 13:31
     */
    int batchInsert(Integer userId, List<Integer> siteIdList);

    /**
     * 用户批量删除网站
     *
     * @param userId
     * @param siteIdList
     * @return affectedRows
     * @author lihaoyu
     * @date 2019/9/22 14:33
     */
    int batchDelete(Integer userId, List<Integer> siteIdList);
}
