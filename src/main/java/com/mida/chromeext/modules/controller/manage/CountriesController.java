package com.mida.chromeext.modules.controller.manage;

import com.mida.chromeext.components.shiro.PermisConstant;
import com.mida.chromeext.modules.pojo.Country;
import com.mida.chromeext.modules.service.CountryService;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.utils.ResultCode;
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
        List<Country> countries = countryService.listAllCountry();
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
        return countryService.updateById(country) ? Result.ok(true) : Result.error();
    }

    @DeleteMapping("{cid}")
    @ApiOperation("删除一条国家（地区）记录")
    @RequiresPermissions(PermisConstant.DELETE_COUNTRY)
    public Result<Boolean> delete(@PathVariable Integer cid) {
        return countryService.deleteById(cid) ? Result.ok(true) : Result.error();
    }
}
