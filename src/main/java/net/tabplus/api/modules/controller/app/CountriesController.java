package net.tabplus.api.modules.controller.app;

import net.tabplus.api.utils.Result;
import net.tabplus.api.modules.pojo.Country;
import net.tabplus.api.modules.service.CountryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/28 19:08
 */
@RestController
@RequestMapping("countries")
@Api(value = "前台国家相关接口", tags = "{}")
public class CountriesController {

    @Autowired
    CountryService countryService;

    /**
     * 查询所有国家，屏蔽敏感字段
     *
     * @return List<Country>
     * @author lihaoyu
     * @date 2019/9/28 19:19
     */
    @GetMapping
    @ApiOperation("获取国家列表")
    public Result<List<Country>> listAllCountry() {
        List<Country> countries = countryService.listAllCountries();
        return Result.ok(countries);
    }
}
