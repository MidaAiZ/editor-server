package com.mida.chromeext.dao;

import com.mida.chromeext.mapper.SiteViewHistoryMapper;
import com.mida.chromeext.pojo.SiteViewHistory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author lihaoyu
 * @date 2019/9/15 20:50
 */
@Repository
public interface SiteViewHistoryDAO extends SiteViewHistoryMapper {
    int insertWithUUID(SiteViewHistory history);
}
