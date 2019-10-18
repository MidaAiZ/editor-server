package com.mida.chromeext.modules.controller.app;

import java.util.List;

import javax.validation.Valid;

import com.google.common.collect.Lists;
import com.mida.chromeext.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mida.chromeext.modules.pojo.Site;
import com.mida.chromeext.modules.service.SiteService;
import com.mida.chromeext.modules.vo.SiteListQueryVo;
import com.mida.chromeext.utils.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author lihaoyu
 * @date 2019/9/24 18:50
 */
@RestController
@RequestMapping("sites")
@Api(value = "前台网站相关接口", tags = "提供网站site相关的 Rest API")
@Validated
public class SitesController {
    @Autowired
    SiteService siteService;

    @GetMapping
    @ApiOperation(value = "网站获取接口", notes = "根据条件查询站点列表，为防止爬虫，前台站点接口需要传入国家(地区)码列表和网站分类ID列表，并限制单次访问最高返回100条数据量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "网站名称关键字", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "countryCodes", value = "国家(地区)码数组，默认为['ALL']", dataType = "String", allowMultiple = true, paramType = "query"),
            @ApiImplicitParam(name = "categoryIds", value = "网站分类cid数组，默认为[0]", dataType = "Integer", allowMultiple = true, paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数，最小为1", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数据量，最大为100", required = true, dataType = "Integer", paramType = "query"),})
    public Result<List<Site>> listSitesByPage(@ApiIgnore @Validated SiteListQueryVo queryVo) {
        if (queryVo.getCountryCodes() == null || queryVo.getCountryCodes().isEmpty()) {
            queryVo.setCountryCodes(Lists.newArrayList(Constant.THE_WORLD));
        }
        if (queryVo.getCategoryIds() == null || queryVo.getCategoryIds().isEmpty()) {
            queryVo.setCategoryIds(Lists.newArrayList(0));
        }

        List<Site> sites = siteService.queryList(queryVo);
        return Result.ok(sites);
    }
}


