package net.tabplus.api.modules.dao.mapper;

import net.tabplus.api.modules.pojo.SiteTracker;
import net.tabplus.api.modules.pojo.SiteTrackerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SiteTrackerMapper {
    long countByExample(SiteTrackerExample example);

    int deleteByExample(SiteTrackerExample example);

    int deleteByPrimaryKey(Integer tid);

    int insert(SiteTracker record);

    int insertSelective(SiteTracker record);

    List<SiteTracker> selectByExampleWithBLOBs(SiteTrackerExample example);

    List<SiteTracker> selectByExample(SiteTrackerExample example);

    SiteTracker selectByPrimaryKey(Integer tid);

    int updateByExampleSelective(@Param("record") SiteTracker record, @Param("example") SiteTrackerExample example);

    int updateByExampleWithBLOBs(@Param("record") SiteTracker record, @Param("example") SiteTrackerExample example);

    int updateByExample(@Param("record") SiteTracker record, @Param("example") SiteTrackerExample example);

    int updateByPrimaryKeySelective(SiteTracker record);

    int updateByPrimaryKeyWithBLOBs(SiteTracker record);

    int updateByPrimaryKey(SiteTracker record);
}