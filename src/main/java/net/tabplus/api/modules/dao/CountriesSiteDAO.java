package net.tabplus.api.modules.dao;

import net.tabplus.api.modules.dao.mapper.CountriesSiteMapper;
import net.tabplus.api.modules.vo.statistic.CountrySitesCount;
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
