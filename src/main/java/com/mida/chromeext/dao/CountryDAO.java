package com.mida.chromeext.dao;

import com.mida.chromeext.mapper.CountryMapper;
import com.mida.chromeext.pojo.Country;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/15 20:48
 */
public interface CountryDAO extends CountryMapper {

    List<Country> listAllCountry();
}
