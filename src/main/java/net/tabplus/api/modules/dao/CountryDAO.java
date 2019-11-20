package net.tabplus.api.modules.dao;

import net.tabplus.api.modules.dao.mapper.CountryMapper;
import net.tabplus.api.modules.pojo.Country;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/15 20:48
 */
@Repository
public interface CountryDAO extends CountryMapper {
    List<Country> selectAll();

    List<Country> selectBySiteId(Integer siteId);
}
