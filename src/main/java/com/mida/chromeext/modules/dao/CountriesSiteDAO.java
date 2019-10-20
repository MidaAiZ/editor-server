package com.mida.chromeext.modules.dao;

import com.mida.chromeext.modules.dao.mapper.CountriesSiteMapper;
import com.mida.chromeext.modules.vo.statistic.CountrySitesCount;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/15 20:47
 */
@Repository
public interface CountriesSiteDAO extends CountriesSiteMapper {

    List<CountrySitesCount> listSitesCountByCountry();

}
