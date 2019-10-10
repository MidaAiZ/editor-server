package com.mida.chromeext.dao;

import com.mida.chromeext.mapper.SearchEngineMapper;
import com.mida.chromeext.pojo.SearchEngine;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/10/7 19:59
 */
@Repository
public interface SearchEngineDAO extends SearchEngineMapper {

    /**
     * 通过国家码查询查询
     *
     * @param countryCode 国家码
     * @return SearchEngine
     * @author lihaoyu
     * @date 2019/10/7 19:42
     */
    SearchEngine getByCountryCode(String countryCode);

    /**
     * 获取所有国家的搜索引擎
     *
     * @return List<SearchEngine>
     * @author lihaoyu
     * @date 2019/10/7 20:02
     */
    List<SearchEngine> listAllSearchEngine();
}
