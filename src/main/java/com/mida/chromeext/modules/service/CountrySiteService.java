package com.mida.chromeext.modules.service;

import com.mida.chromeext.modules.dao.CountriesSiteDAO;
import com.mida.chromeext.modules.vo.statistic.StatisticSiteByCountry;
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

    public List<StatisticSiteByCountry> listCountByCountry(){
        return countriesSiteDAO.listCountByCountry();
    }


}
