package net.tabplus.api.modules.dao.mapper;

import java.util.List;
import net.tabplus.api.modules.pojo.SiteViewHistory;
import net.tabplus.api.modules.pojo.SiteViewHistoryExample;
import org.apache.ibatis.annotations.Param;

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