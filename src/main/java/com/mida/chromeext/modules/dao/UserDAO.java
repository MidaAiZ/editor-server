package com.mida.chromeext.modules.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mida.chromeext.modules.dao.mapper.UserMapper;
import com.mida.chromeext.modules.vo.statistic.StatisticCountVo;
import com.mida.chromeext.modules.vo.statistic.StatisticUserByCountry;

/**
 * @author lihaoyu
 * @date 2019/9/15 20:50
 */
@Repository
public interface UserDAO extends UserMapper {

    List<StatisticCountVo> dailyCounts(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

    List<StatisticCountVo> monthlyCounts(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

    List<StatisticUserByCountry> listCountsByCountry();

}
