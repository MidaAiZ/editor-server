package com.mida.chromeext.dao;

import com.mida.chromeext.mapper.UserSiteMapper;
import com.mida.chromeext.pojo.Site;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/15 20:50
 */
public interface UserSiteDAO extends UserSiteMapper {

    List<Site> listSiteByUserId(Integer userId);

}
