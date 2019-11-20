package net.tabplus.api.modules.dao;

import net.tabplus.api.modules.dao.mapper.UserMapper;
import net.tabplus.api.modules.vo.statistic.CountryUsersCount;
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
public interface UserDAO extends UserMapper {

    List<StatisticCountVo> listDailyAliveUsersCount(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

    List<StatisticCountVo> listDailyNewUsersCount(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

    List<StatisticCountVo> listMonthlyAliveUsersCount(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

    List<CountryUsersCount> listUsersCountByCountry();

}
