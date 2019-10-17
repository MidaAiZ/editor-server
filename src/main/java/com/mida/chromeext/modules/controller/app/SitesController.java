package com.mida.chromeext.modules.controller.app;

import java.util.List;

import javax.validation.Valid;

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
    @ApiOperation(value = "网站获取接口", notes = "分页方式获取网站，也可以全部获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyWord", value = "网站名称关键字", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "countryCodeList", value = "国家id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "categoryIdList", value = "网站类型Id", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "查询第几页，最小为1", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条，为0时查询全部数据", required = true, dataType = "Integer", paramType = "query"),})
    public Result<List<Site>> listSitesByPage(@ApiIgnore @Validated SiteListQueryVo queryVo) {
        List<Site> sites = siteService.listSitesByPage(queryVo);
        return Result.ok(sites);
    }
}


