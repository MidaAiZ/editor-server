package com.mida.chromeext.modules.dao;

import com.mida.chromeext.modules.dao.mapper.SiteViewHistoryMapper;
import com.mida.chromeext.modules.pojo.SiteViewHistory;
import org.springframework.stereotype.Repository;

/**
 * @author lihaoyu
 * @date 2019/9/15 20:50
 */
@Repository
public interface SiteViewHistoryDAO extends SiteViewHistoryMapper {
    int insertWithUUID(SiteViewHistory history);
}
