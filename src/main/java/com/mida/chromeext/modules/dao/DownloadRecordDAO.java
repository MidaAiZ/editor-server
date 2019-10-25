package com.mida.chromeext.modules.dao;

import com.mida.chromeext.modules.dao.mapper.DownloadRecordMapper;
import com.mida.chromeext.modules.pojo.DownloadRecord;
import com.mida.chromeext.modules.vo.statistic.CountryUsersCount;
import com.mida.chromeext.modules.vo.statistic.StatisticCountVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface DownloadRecordDAO extends DownloadRecordMapper {
    int insertWithId(DownloadRecord record);

    List<StatisticCountVo> listDailyDloadsCount(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

    List<StatisticCountVo> listMonthlyDloadsCount(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
}
