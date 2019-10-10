package com.mida.chromeext.controller;

import com.mida.chromeext.pojo.Country;
import com.mida.chromeext.service.CountryService;
import com.mida.chromeext.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/28 19:08
 */
@RestController
@RequestMapping("countries")
@Api(value = "国家相关接口", tags = "{}")
public class CountryController {

    @Autowired
    CountryService countryService;

    /**
     * 查询所有国家，屏蔽敏感字段
     *
     * @return List<Country>
     * @author lihaoyu
     * @date 2019/9/28 19:19
     */
    public Result<List<Country>> listAllCountry() {
        List<Country> countries = countryService.listAllCountry();
        return Result.ok(countries);
    }


}
