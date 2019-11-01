package com.mida.chromeext.modules.service;

import com.github.pagehelper.PageHelper;
import com.mida.chromeext.modules.dao.CountryDAO;
import com.mida.chromeext.modules.pojo.Country;
import com.mida.chromeext.modules.pojo.CountryExample;
import com.mida.chromeext.modules.pojo.User;
import com.mida.chromeext.modules.vo.ListQueryVo;
import com.mida.chromeext.modules.vo.SiteListQueryVo;
import org.apache.commons.collections.ListUtils;
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
    private CountryDAO countryDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private CountriesSitesService countriesSitesService;

    /**
     * 查询所有国家
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
     *
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
     * @param country
     * @return
     */
    public Boolean updateById(Country country) {
        return countryDAO.updateByPrimaryKeySelective(country) > 0;
    }

    /**
     * 删除一条数据
     * 只有国家没有关联的情况下才能够被删除
     * 否则将删除失败
     * @param country
     * @return
     */
    public Boolean deleteOne(Country country) {
        if (country.getCode() == null || country.getCid() == null) {
            return false;
        }
        // 已有用户绑定，禁止删除
        if (userService.getUsersCountByCountryCode(country.getCode()) > 0) {
            return false;
        }
        // 已有网站绑定，禁止删除
        if (countriesSitesService.getSitesCountByCountryCode(country.getCode()) > 0) {
            return false;
        }
        return countryDAO.deleteByPrimaryKey(country.getCid()) > 0;
    }

    /**
     * 根据国家（地区）码获取一条数据
     * @param code
     * @return
     */
    public Country getOneByCode(String code) {
        CountryExample ex = new CountryExample();
        ex.createCriteria().andCodeEqualTo(code);
        PageHelper.startPage(1, 1, false);
        List<Country> cs = countryDAO.selectByExample(ex);
        if (cs == null || cs.isEmpty()) {
            return null;
        }
        return cs.get(0);
    }

    /**
     * 根据id获取一条国家记录
     * @param cid
     * @return
     */
    public Country getOneById(Integer cid) {
        return countryDAO.selectByPrimaryKey(cid);
    }

}
