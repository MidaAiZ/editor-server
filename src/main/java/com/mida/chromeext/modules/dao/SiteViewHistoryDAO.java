package com.mida.chromeext.modules.dao;

import com.mida.chromeext.modules.dao.mapper.SiteViewHistoryMapper;
import com.mida.chromeext.modules.pojo.SiteViewHistory;
import com.mida.chromeext.modules.vo.statistic.StatisticCountVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/15 20:50
 */
@Repository
public interface SiteViewHistoryDAO extends SiteViewHistoryMapper {
    int insertSelectiveWithUUID(SiteViewHistory history);

    List<StatisticCountVo> listDailyViewCount(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

    List<StatisticCountVo> listMonthlyViewCount(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
}
