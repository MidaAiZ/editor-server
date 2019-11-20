package net.tabplus.api.modules.dao;

import net.tabplus.api.modules.dao.mapper.SiteTrackerMapper;
import net.tabplus.api.modules.pojo.SiteTracker;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteTrackerDAO extends SiteTrackerMapper {
    SiteTracker selectByCountyCode(String code);
}
