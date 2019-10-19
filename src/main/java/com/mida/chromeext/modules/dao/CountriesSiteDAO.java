package com.mida.chromeext.modules.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mida.chromeext.modules.dao.mapper.CountriesSiteMapper;
import com.mida.chromeext.modules.vo.statistic.StatisticSiteByCountry;

/**
 * @author lihaoyu
 * @date 2019/9/15 20:47
 */
@Repository
public interface CountriesSiteDAO extends CountriesSiteMapper {

    List<StatisticSiteByCountry> listCountByCountry();

}
