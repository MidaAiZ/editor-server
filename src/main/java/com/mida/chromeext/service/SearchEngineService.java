package com.mida.chromeext.service;

import com.alibaba.fastjson.JSON;
import com.mida.chromeext.dao.SearchEngineDAO;
import com.mida.chromeext.dto.EngineDto;
import com.mida.chromeext.dto.SearchEngineAddDto;
import com.mida.chromeext.pojo.SearchEngine;
import com.mida.chromeext.pojo.SearchEngineExample;
import com.mida.chromeext.utils.Constant;
import com.mida.chromeext.utils.NumConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/10/7 19:30
 */
@Service
public class SearchEngineService {

    @Autowired
    private SearchEngineDAO searchEngineDAO;

    /**
     * 通过国家码查询查询
     *
     * @param countryCode 国家码
     * @return SearchEngine
     * @author lihaoyu
     * @date 2019/10/7 19:42
     */
    public SearchEngine getSearchEngine(String countryCode) {
        SearchEngine searchEngine = null;
        if (!StringUtils.isEmpty(countryCode)) {
            searchEngine = searchEngineDAO.getByCountryCode(countryCode);
        }
        // 该国家没有默认值，则使用ALL的默认值
        if (searchEngine == null) {
            searchEngine = searchEngineDAO.getByCountryCode(Constant.THE_WORLD);
        }
        return searchEngine;
    }

    /**
     * 获取所有国家的搜索引擎
     *
     * @return List<SearchEngine>
     * @author lihaoyu
     * @date 2019/10/7 20:02
     */
    public List<SearchEngine> listAllSearchEngine() {
        return searchEngineDAO.listAllSearchEngine();
    }

    /**
     * 添加某个国家的搜索引擎
     *
     * @param dto 前端传入
     * @return SearchEngine
     * @author lihaoyu
     * @date 2019/10/7 20:47
     */
    @Transactional(rollbackFor = Exception.class)
    public SearchEngine addSearchEngine(SearchEngineAddDto dto) {
        SearchEngine searchEngine = searchEngineDAO.getByCountryCode(dto.getCountryCode());
        // 已经存在，不能添加
        if (searchEngine != null) {
            return null;
        }
        List<EngineDto> engineDtoList = dto.getEngineDtoList();
        String engineJson = JSON.toJSONString(engineDtoList);
        Date date = new Date();
        searchEngine = SearchEngine.builder().countryCode(dto.getCountryCode()).createdAt(date).updatedAt(date).engines(engineJson).createdBy(NumConst.NUM0).build();
        searchEngineDAO.insert(searchEngine);
        return searchEngine;
    }

    /**
     * 根据国家码进行删除
     *
     * @param
     * @return int 1为成功删除
     * @author lihaoyu
     * @date 2019/10/8 16:37
     */
    @Transactional(rollbackFor = Exception.class)
    public int deleteSearchEngine(String code) {
        SearchEngineExample example = new SearchEngineExample();
        example.createCriteria().andCountryCodeEqualTo(code);
        int affectRow = searchEngineDAO.deleteByExample(example);
        return affectRow;
    }


}
