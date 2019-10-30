package com.mida.chromeext.modules.dao;

import com.mida.chromeext.modules.dao.mapper.SiteTrackerMapper;
import com.mida.chromeext.modules.pojo.SiteTracker;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteTrackerDAO extends SiteTrackerMapper {
    SiteTracker selectByCountyCode(String code);
}
