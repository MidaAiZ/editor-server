package com.mida.chromeext.mapper;

import com.mida.chromeext.pojo.CountriesSite;
import com.mida.chromeext.pojo.CountriesSiteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CountriesSiteMapper {
    int countByExample(CountriesSiteExample example);

    int deleteByExample(CountriesSiteExample example);

    int insert(CountriesSite record);

    int insertSelective(CountriesSite record);

    List<CountriesSite> selectByExample(CountriesSiteExample example);

    int updateByExampleSelective(@Param("record") CountriesSite record, @Param("example") CountriesSiteExample example);

    int updateByExample(@Param("record") CountriesSite record, @Param("example") CountriesSiteExample example);
}