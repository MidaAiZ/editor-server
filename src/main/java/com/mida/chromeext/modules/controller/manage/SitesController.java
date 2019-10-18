package com.mida.chromeext.modules.controller.manage;

import com.mida.chromeext.annotation.CurrentAdmin;
import com.mida.chromeext.components.shiro.PermisConstant;
import com.mida.chromeext.modules.pojo.Admin;
import com.mida.chromeext.modules.pojo.Site;
import com.mida.chromeext.modules.service.SiteService;
import com.mida.chromeext.modules.vo.SiteAddVo;
import com.mida.chromeext.modules.vo.SiteListQueryVo;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.utils.ResultCode;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;


@RestController("mngSitesController")
@RequestMapping("manage/sites")
@Api(value = "后台网站相关接口", tags = "提供网站site相关的 Rest API")
@Validated
public class SitesController {
    @Autowired
    SiteService siteService;

    @GetMapping
    @ApiOperation(value = "网站获取接口", notes = "分页方式查询获取网站，包含网站关联分类和创建网站的管理员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "网站名称关键字", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "countryCodes", value = "国家(地区)码数组", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "categoryIds", value = "网站分类cid数组", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数，最小为1", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数据量，最大为100", required = true, dataType = "Integer", paramType = "query"),})
    @RequiresPermissions(PermisConstant.SHOW_SITE)
    public List<Site> listSitesByPage(@ApiIgnore @Validated SiteListQueryVo queryVo) {
        List<Site> sites = siteService.queryListWithRelations(queryVo);
        return sites;
    }

    @GetMapping("{sid}")
    @ApiOperation(value = "通过网站id获取网站详细信息", notes = "包括关联对象，管理员、国家(地区)、分类等信息")
    @RequiresPermissions(PermisConstant.SHOW_SITE)
    public Result<Site> showSite(@PathVariable Integer sid) {
        Site site = siteService.getSiteByIdWithRelations(sid);
        return site != null ? Result.ok(site) : Result.error(ResultCode.NOT_FOUND.code(), "No suc site with sid = " + sid.toString());
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
