package com.mida.chromeext.mapper;

import com.mida.chromeext.pojo.SearchEngine;
import com.mida.chromeext.pojo.SearchEngineExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SearchEngineMapper {
    long countByExample(SearchEngineExample example);

    int deleteByExample(SearchEngineExample example);

    int deleteByPrimaryKey(Integer eid);

    int insert(SearchEngine record);

    int insertSelective(SearchEngine record);

    List<SearchEngine> selectByExampleWithBLOBs(SearchEngineExample example);

    List<SearchEngine> selectByExample(SearchEngineExample example);

    SearchEngine selectByPrimaryKey(Integer eid);

    int updateByExampleSelective(@Param("record") SearchEngine record, @Param("example") SearchEngineExample example);

    int updateByExampleWithBLOBs(@Param("record") SearchEngine record, @Param("example") SearchEngineExample example);

    int updateByExample(@Param("record") SearchEngine record, @Param("example") SearchEngineExample example);

    int updateByPrimaryKeySelective(SearchEngine record);

    int updateByPrimaryKeyWithBLOBs(SearchEngine record);

    int updateByPrimaryKey(SearchEngine record);
}