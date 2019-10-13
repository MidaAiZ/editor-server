package com.mida.chromeext.modules.service;

import com.mida.chromeext.modules.dao.CountryDAO;
import com.mida.chromeext.modules.pojo.Country;
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
    public List<Country> listAllCountry() {
        List<Country> countries = countryDAO.listAllCountry();
        return countries;
    }
}
