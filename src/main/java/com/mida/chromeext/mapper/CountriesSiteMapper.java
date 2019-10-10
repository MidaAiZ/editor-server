package com.mida.chromeext.mapper;

import com.mida.chromeext.pojo.CountriesSite;
import com.mida.chromeext.pojo.CountriesSiteExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CountriesSiteMapper {
    long countByExample(CountriesSiteExample example);

    int deleteByExample(CountriesSiteExample example);

    int insert(CountriesSite record);

    int insertSelective(CountriesSite record);

    List<CountriesSite> selectByExample(CountriesSiteExample example);

    int updateByExampleSelective(@Param("record") CountriesSite record, @Param("example") CountriesSiteExample example);

    int updateByExample(@Param("record") CountriesSite record, @Param("example") CountriesSiteExample example);
}