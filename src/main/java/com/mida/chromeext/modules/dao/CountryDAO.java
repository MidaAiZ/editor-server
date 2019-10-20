package com.mida.chromeext.modules.dao;

import com.mida.chromeext.modules.dao.mapper.CountryMapper;
import com.mida.chromeext.modules.pojo.Country;
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
