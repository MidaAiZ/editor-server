package net.tabplus.api.modules.controller.manage;

import net.tabplus.api.components.shiro.PermisConstant;
import net.tabplus.api.modules.pojo.Country;
import net.tabplus.api.modules.service.CountryService;
import net.tabplus.api.utils.Result;
import net.tabplus.api.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/28 19:08
 */
@RestController("mngCountriesController")
@RequestMapping("manage/countries")
@Api(value = "后台国家（地区）相关接口", tags = "{}")
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
    @ApiOperation("获取国家(地区)列表")
    public Result<List<Country>> listAllCountry() {
        List<Country> countries = countryService.listAllCountries();
        return Result.ok(countries);
    }

    @GetMapping("{code}")
    @ApiOperation("通过唯一国家(地区)码获取一条数据")
    public Result<Country> show(@PathVariable String code) {
        Country country = countryService.getOneByCode(code);
        return country != null ? Result.ok(country) : Result.error(ResultCode.NOT_FOUND.code(), "No such country with code = " + code);
    }

    @PostMapping()
    @ApiOperation("新建国家(地区)")
    @RequiresPermissions(PermisConstant.ADD_COUNTRY)
    public Result<Country> create(@RequestBody @Validated Country country) {
        if (countryService.create(country)) {
            return Result.ok(country);
        } else {
            return Result.error();
        }
    }

    @PutMapping("{cid}")
    @ApiOperation("通过国家(地区)主键更新一条数据")
    @RequiresPermissions(PermisConstant.MODIFY_COUNTRY)
    public Result<Boolean> update(@PathVariable Integer cid, @RequestBody Country country) {
        country.setCid(cid);
        Country existCountry = countryService.getOneById(cid);
        if (existCountry == null) {
            return Result.error(ResultCode.NOT_FOUND.code(), "No such country with ID = " + cid.toString());
        }
        return countryService.updateById(country) ? Result.ok(true) : Result.error();
    }

    @DeleteMapping("{cid}")
    @ApiOperation("删除一条国家（地区）记录，若该国家已有用户、网站等关联绑定，则无法删除")
    @RequiresPermissions(PermisConstant.DELETE_COUNTRY)
    public Result<Boolean> delete(@PathVariable Integer cid) {
        Country country = countryService.getOneById(cid);
        if (country == null) {
            return Result.error(ResultCode.NOT_FOUND.code(), "No such country with ID = " + cid.toString());
        }
        return countryService.deleteOne(country) ? Result.ok(true) : Result.error();
    }
}
