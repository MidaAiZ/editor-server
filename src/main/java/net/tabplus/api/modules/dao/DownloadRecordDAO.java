package net.tabplus.api.modules.dao;

import net.tabplus.api.modules.pojo.DownloadRecord;
import net.tabplus.api.modules.vo.statistic.StatisticCountVo;
import net.tabplus.api.modules.dao.mapper.DownloadRecordMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface DownloadRecordDAO extends DownloadRecordMapper {
    int insertWithId(DownloadRecord record);

    List<StatisticCountVo> listDailyDloadsCount(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

    List<StatisticCountVo> listMonthlyDloadsCount(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
}
