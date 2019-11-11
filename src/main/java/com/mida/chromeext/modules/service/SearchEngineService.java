package com.mida.chromeext.modules.service;

import com.alibaba.fastjson.JSON;
import com.mida.chromeext.modules.dao.SearchEngineDAO;
import com.mida.chromeext.modules.dto.SearchEngineAddDto;
import com.mida.chromeext.modules.dto.SearchEngineItemDto;
import com.mida.chromeext.modules.pojo.Admin;
import com.mida.chromeext.modules.pojo.SearchEngine;
import com.mida.chromeext.modules.pojo.SearchEngineExample;
import com.mida.chromeext.modules.vo.SeachEngineRelVo;
import com.mida.chromeext.utils.Constant;
import com.mida.chromeext.utils.NumConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
    public List<SeachEngineRelVo> listAllSearchEngine() {
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
    public SearchEngine addSearchEngine(SearchEngineAddDto dto, Admin admin) {
        Date date = new Date();
        SearchEngine searchEngine = SearchEngine.builder().countryCode(dto.getCountryCode()).createdAt(date).updatedAt(date).engines(JSON.toJSONString(dto.getEngines())).createdBy(admin.getAid()).build();
        // 已经存在，覆盖式添加
        if (searchEngineDAO.getByCountryCode(dto.getCountryCode()) != null) {
            updateByCountryCode(searchEngine);
            return searchEngine;
        }

        return searchEngineDAO.insert(searchEngine) > 0 ? searchEngine : null;

    }

    /**
     * 批量添加
     *
     * @param engineDtoList
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public List<SearchEngine> addSearchEngineList(List<SearchEngineAddDto> engineDtoList, Admin admin) {
        List<SearchEngine> engineList = new ArrayList<>();
        for (SearchEngineAddDto dto : engineDtoList) {
            SearchEngine record = addSearchEngine(dto, admin);
            if (record != null) {
                engineList.add(record);
            }
        }
        return engineList;
    }

    /**
     * 根据countryCode更新记录
     *
     * @param record
     * @return
     */
    public Boolean updateByCountryCode(SearchEngine record) {
        SearchEngineExample example = new SearchEngineExample();
        example.createCriteria().andCountryCodeEqualTo(record.getCountryCode());
        record.setUpdatedAt(new Date());
        return searchEngineDAO.updateByExampleSelective(record, example) > 0;
    }

    /**
     * 删除默认搜索引擎列表
     *
     * @param
     * @return int 1为成功删除
     * @author lihaoyu
     * @date 2019/10/8 16:37
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteSearchEngines(List<Integer> eids) {
        SearchEngineExample example = new SearchEngineExample();
        example.createCriteria().andEidIn(eids);
        return searchEngineDAO.deleteByExample(example);
    }


}
