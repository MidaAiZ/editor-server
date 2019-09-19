package com.mida.chromeext.dao;

import com.mida.chromeext.mapper.SiteMapper;

/**
 * @author lihaoyu
 * @date 2019/9/15 20:49
 */
public interface SiteDAO extends SiteMapper {

    int increaseUsedCount(Integer sid);

    int decreaseUsedCount(Integer sid);
}
