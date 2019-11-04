package com.mida.chromeext.modules.dao.mapper;

import com.mida.chromeext.modules.pojo.SiteTracker;
import com.mida.chromeext.modules.pojo.SiteTrackerExample;
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