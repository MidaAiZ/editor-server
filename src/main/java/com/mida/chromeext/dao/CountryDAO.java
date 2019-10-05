package com.mida.chromeext.dao;

import com.mida.chromeext.mapper.CountryMapper;
import com.mida.chromeext.pojo.Country;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/15 20:48
 */
@Repository
public interface CountryDAO extends CountryMapper {

    List<Country> listAllCountry();
}
