package com.mida.chromeext.modules.service;

import com.github.pagehelper.PageHelper;
import com.mida.chromeext.modules.dao.CountryDAO;
import com.mida.chromeext.modules.pojo.Country;
import com.mida.chromeext.modules.pojo.CountryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/28 19:08
 */
@Service
public class CountryService {

    @Autowired
    CountryDAO countryDAO;

    /**
     * 查询所有国家，屏蔽敏感字段
     *
     * @return Country List
     * @author lihaoyu
     * @date 2019/9/28 19:18
     */
    public List<Country> listAllCountries() {
        List<Country> countries = countryDAO.selectAll();
        return countries;
    }


    /**
     * 通过站点ID获取关联的国家(地区)列表
     * @param siteId
     * @return
     */
    public List<Country> listCountriesBySiteId(Integer siteId) {
        List<Country> countries = countryDAO.selectBySiteId(siteId);
        return countries;
    }

    /**
     * 插入一条新数据
     */
    public Boolean create(Country country) {
        return countryDAO.insertSelective(country) > 0;
    }

    /**
     * 更新一条数据
     */
    public Boolean updateById(Country country) {
        return countryDAO.updateByPrimaryKeySelective(country) > 0;
    }

    /**
     * 删除一条数据
     */
    public Boolean deleteById(Integer cid) {
        return countryDAO.deleteByPrimaryKey(cid) > 0;
    }

    /**
     * 获取一条数据
     */
    public Country getOneByCode(String code) {
        CountryExample ex = new CountryExample();
        ex.createCriteria().andCodeEqualTo(code);
        PageHelper.startPage(1, 1);
        List<Country> cs = countryDAO.selectByExample(ex);
        if (cs == null || cs.isEmpty()) { return null; }
        return cs.get(0);
    }

}
