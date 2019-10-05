package com.mida.chromeext.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mida.chromeext.annotation.CurrentUser;
import com.mida.chromeext.pojo.Site;
import com.mida.chromeext.pojo.User;
import com.mida.chromeext.service.SiteService;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.vo.SiteAddVo;
import com.mida.chromeext.vo.SiteListQueryVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author lihaoyu
 * @date 2019/9/24 18:50
 */
@RestController
@RequestMapping("sites")
@Api(tags = "网站相关接口", description = "提供网站site相关的 Rest API")
@Validated
public class SiteController {

    @Autowired
    SiteService siteService;

    @PostMapping("list")
    @ApiOperation(value = "网站获取接口", notes = "分页方式获取网站，也可以全部获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyWord", value = "网站名称关键字", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "countryId", value = "国家id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "siteCategory", value = "网站类型对象", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "查询第几页，最小为1", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条，为0时查询全部数据", required = true, dataType = "Integer", paramType = "query"),})
    public List<Site> listSitesByPage(@RequestBody @ApiParam("分页查询参数") SiteListQueryVo queryVo) {
        List<Site> sites = siteService.listSitesByPage(queryVo);
        return sites;
    }

    /**
     * 管理员添加网站  同时添加 site 和 country 关联
     *
     * @param siteAddVos
     * @return List<Site>
     * @author lihaoyu
     * @date 2019/9/28 21:27
     */
    //@LoginRequired
    @PostMapping("")
    public Result<List<Site>> addSites(@Valid @RequestBody List<SiteAddVo> siteAddVos, @CurrentUser User user){
        List<Site> sites;
        try {
            sites = siteService.addSites(siteAddVos, 0);
            // sites = siteService.addSites(siteAddVos, user.getUid());
        }catch (Exception ex){
            return Result.error("");
        }
        return Result.ok(sites);
    }


}


