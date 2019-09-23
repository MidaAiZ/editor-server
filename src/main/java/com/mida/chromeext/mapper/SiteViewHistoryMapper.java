package com.mida.chromeext.mapper;

import com.mida.chromeext.pojo.SiteViewHistory;
import com.mida.chromeext.pojo.SiteViewHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SiteViewHistoryMapper {
    int countByExample(SiteViewHistoryExample example);

    int deleteByExample(SiteViewHistoryExample example);

    int deleteByPrimaryKey(String hid);

    int insert(SiteViewHistory record);

    int insertSelective(SiteViewHistory record);

    List<SiteViewHistory> selectByExampleWithBLOBs(SiteViewHistoryExample example);

    List<SiteViewHistory> selectByExample(SiteViewHistoryExample example);

    SiteViewHistory selectByPrimaryKey(String hid);

    int updateByExampleSelective(@Param("record") SiteViewHistory record, @Param("example") SiteViewHistoryExample example);

    int updateByExampleWithBLOBs(@Param("record") SiteViewHistory record, @Param("example") SiteViewHistoryExample example);

    int updateByExample(@Param("record") SiteViewHistory record, @Param("example") SiteViewHistoryExample example);

    int updateByPrimaryKeySelective(SiteViewHistory record);

    int updateByPrimaryKeyWithBLOBs(SiteViewHistory record);

    int updateByPrimaryKey(SiteViewHistory record);
}