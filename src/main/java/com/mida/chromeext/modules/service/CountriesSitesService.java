package com.mida.chromeext.modules.service;

import com.mida.chromeext.modules.dao.CountriesSiteDAO;
import com.mida.chromeext.modules.pojo.CountriesSite;
import com.mida.chromeext.modules.pojo.CountriesSiteExample;
import com.mida.chromeext.modules.pojo.Site;
import com.mida.chromeext.modules.vo.statistic.CountrySitesCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 国家（地区）- 网站多对多关联
 */
@Service
public class CountriesSitesService {

    @Autowired
    private CountriesSiteDAO countriesSiteDAO;

    @Autowired
    private SiteService siteService;

    /**
     * 获取所有国家下的网站数量
     *
     * @return
     */
    public List<CountrySitesCount> listSitesCountByCountry() {
        return countriesSiteDAO.listSitesCountByCountry();
    }

    /**
     * 根据国家码获取关联网站数
     */
    public long getSitesCountByCountryCode(String code) {
        CountriesSiteExample example = new CountriesSiteExample();
        example.createCriteria().andCountryCodeEqualTo(code);
        return countriesSiteDAO.countByExample(example);
    }

    /**
     * 添加网站-国家关联
     *
     * @param siteId
     * @param countryCodes
     * @return
     */
    public int AddRelations(Integer siteId, List<String> countryCodes) {
        int count = 0;
        Site site = siteService.getSiteById(siteId);
        if (site == null) {
            return 0;
        }
        for (String code : countryCodes) {
            CountriesSite cs = new CountriesSite();
            cs.setSiteId(siteId);
            cs.setCountryCode(code);
            countriesSiteDAO.insert(cs);
            count++;
        }
        return count;
    }

    /**
     * 移除关系
     *
     * @param siteId
     * @param countryCodes
     * @return
     */
    public int removeRelations(Integer siteId, List<String> countryCodes) {
        CountriesSiteExample example = new CountriesSiteExample();
        example.createCriteria().andSiteIdEqualTo(siteId).andCountryCodeIn(countryCodes);
        return countriesSiteDAO.deleteByExample(example);
    }

    /**
     * 通过国家(地区)码移出关系
     *
     * @param countryCode
     * @return
     */
    public int removeRelationsByCode(String countryCode) {
        CountriesSiteExample example = new CountriesSiteExample();
        example.createCriteria().andCountryCodeEqualTo(countryCode);
        return countriesSiteDAO.deleteByExample(example);
    }

    /**
     * 通过网站id移除关系
     *
     * @param siteId
     * @return
     */
    public int removeRelationsBySiteId(Integer siteId) {
        CountriesSiteExample example = new CountriesSiteExample();
        example.createCriteria().andSiteIdEqualTo(siteId);
        return countriesSiteDAO.deleteByExample(example);
    }
}
