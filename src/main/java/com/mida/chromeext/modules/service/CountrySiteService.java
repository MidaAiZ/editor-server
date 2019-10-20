package com.mida.chromeext.modules.service;

import com.mida.chromeext.modules.dao.CountriesSiteDAO;
import com.mida.chromeext.modules.vo.statistic.CountrySitesCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/10/19 20:59
 */
@Service
public class CountrySiteService {

    @Autowired
    CountriesSiteDAO countriesSiteDAO;

    /**
     * 获取所有国家下的网站数量
     * @return
     */
    public List<CountrySitesCount> listSitesCountByCountry() {
        return countriesSiteDAO.listSitesCountByCountry();
    }

}
