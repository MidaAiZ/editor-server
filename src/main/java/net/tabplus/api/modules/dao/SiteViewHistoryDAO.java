package net.tabplus.api.modules.dao;

import net.tabplus.api.modules.dao.mapper.SiteViewHistoryMapper;
import net.tabplus.api.modules.pojo.SiteViewHistory;
import net.tabplus.api.modules.vo.statistic.StatisticCountVo;
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
