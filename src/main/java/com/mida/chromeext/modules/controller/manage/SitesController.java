package com.mida.chromeext.modules.controller.manage;

import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mida.chromeext.annotation.CurrentAdmin;
import com.mida.chromeext.components.shiro.PermisConstant;
import com.mida.chromeext.modules.pojo.Admin;
import com.mida.chromeext.modules.pojo.Site;
import com.mida.chromeext.modules.service.SiteService;
import com.mida.chromeext.modules.vo.SiteAddVo;
import com.mida.chromeext.modules.vo.SiteListQueryVo;
import com.mida.chromeext.utils.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController("mngSitesController")
@RequestMapping("manage/sites")
@Api(value = "后台网站相关接口", tags = "提供网站site相关的 Rest API")
@Validated
public class SitesController {
    @Autowired
    SiteService siteService;

    @GetMapping
    @ApiOperation(value = "网站获取接口", notes = "分页方式获取网站，也可以全部获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyWord", value = "网站名称关键字", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "countryCodeList", value = "国家码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "categoryIdList", value = "网站类型id", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "查询第几页，最小为1", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条，为0时查询全部数据", required = true, dataType = "Integer", paramType = "query"),})
    @RequiresPermissions(PermisConstant.SHOW_SITE)
    public List<Site> listSitesByPage(@ApiIgnore @Validated SiteListQueryVo queryVo) {
        List<Site> sites = siteService.listSitesByPage(queryVo);
        return sites;
    }

    /**
     * 管理员添加网站  同时添加 site 和 country 关联
     *
     * @param sites
     * @return List<Site>
     */
    @PostMapping("")
    @ApiOperation("添加网站")
    @RequiresPermissions(PermisConstant.ADD_SITE)
    public Result<List<Site>> addSites(@Valid @RequestBody List<SiteAddVo> sites, @ApiIgnore @CurrentAdmin Admin admin) {
        List<Site> insertedSiteList;
        try {
            insertedSiteList = siteService.addSites(sites, admin.getAid());
        } catch (Exception ex) {
            return Result.error("");
        }
        return Result.ok(insertedSiteList);
    }

    @PutMapping("{sid}")
    @ApiOperation("更新一条网站数据")
    @RequiresPermissions(PermisConstant.MODIFY_SITE)
    public Result<Boolean> update(@PathVariable Integer sid, @ApiParam("待更新的网站记录") @RequestBody Site site) {
        site.setSid(sid);
        return siteService.update(site) ? Result.ok(true) : Result.error();
    }

}
