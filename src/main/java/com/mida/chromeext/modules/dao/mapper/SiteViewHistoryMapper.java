package com.mida.chromeext.modules.dao.mapper;

import com.mida.chromeext.modules.pojo.SiteViewHistory;
import com.mida.chromeext.modules.pojo.SiteViewHistoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SiteViewHistoryMapper {
    long countByExample(SiteViewHistoryExample example);

    int deleteByExample(SiteViewHistoryExample example);

    int deleteByPrimaryKey(String hid);

    int insert(SiteViewHistory record);

    int insertSelective(SiteViewHistory record);

    List<SiteViewHistory> selectByExample(SiteViewHistoryExample example);

    SiteViewHistory selectByPrimaryKey(String hid);

    int updateByExampleSelective(@Param("record") SiteViewHistory record, @Param("example") SiteViewHistoryExample example);

    int updateByExample(@Param("record") SiteViewHistory record, @Param("example") SiteViewHistoryExample example);

    int updateByPrimaryKeySelective(SiteViewHistory record);

    int updateByPrimaryKey(SiteViewHistory record);
}