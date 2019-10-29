package com.mida.chromeext.modules.controller.manage;

import com.mida.chromeext.annotation.CurrentAdmin;
import com.mida.chromeext.components.shiro.PermisConstant;
import com.mida.chromeext.modules.pojo.Admin;
import com.mida.chromeext.modules.pojo.Site;
import com.mida.chromeext.modules.pojo.SiteCategory;
import com.mida.chromeext.modules.service.CountriesSitesService;
import com.mida.chromeext.modules.service.SiteCategoryService;
import com.mida.chromeext.modules.service.SiteService;
import com.mida.chromeext.modules.vo.ListResultVo;
import com.mida.chromeext.modules.vo.SiteAddVo;
import com.mida.chromeext.modules.vo.SiteListQueryVo;
import com.mida.chromeext.modules.vo.SiteRelationVo;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.utils.ResultCode;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;


@RestController("mngSitesController")
@RequestMapping("manage/sites")
@Api(value = "后台网站相关接口", tags = "提供网站site相关的 Rest API")
@Validated
@Slf4j
public class SitesController {
    @Autowired
    private SiteService siteService;

    @Autowired
    private CountriesSitesService countriesSitesService;

    @Autowired
    private SiteCategoryService siteCategoryService;

    @GetMapping
    @ApiOperation(value = "网站获取接口", notes = "分页方式查询获取网站，包含网站关联分类和创建网站的管理员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "state", value = "网站状态，0表示审核中或未审核，1表示正常可访问，2表示网站被屏蔽", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "keyword", value = "网站名称关键字", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "countryCodes", value = "国家(地区)码数组", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "categoryIds", value = "网站分类cid数组", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "creatorType", value = "网站创建者类型，0表示由管理员创建，1表示由用户创建", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "creatorIds", value = "创建者Id列表，需要配合creatorType使用，指明创建者类型", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数，最小为1", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数据量，最大为100", required = true, dataType = "Integer", paramType = "query"),})
    @RequiresPermissions(PermisConstant.SHOW_SITE)
    public Result<ListResultVo<SiteRelationVo>> listSitesByPage(@ApiIgnore @Validated SiteListQueryVo queryVo) {
        ListResultVo<SiteRelationVo> sites = siteService.queryListWithRelations(queryVo);
        return Result.ok(sites);
    }

    @GetMapping("{siteId}")
    @ApiOperation(value = "通过网站id获取网站详细信息", notes = "包括关联对象，管理员、国家(地区)、分类等信息")
    @RequiresPermissions(PermisConstant.SHOW_SITE)
    public Result<SiteRelationVo> showSite(@PathVariable Integer siteId) {
        SiteRelationVo site = siteService.getSiteByIdWithRelations(siteId);
        return site != null ? Result.ok(site) : Result.error(ResultCode.NOT_FOUND.code(), "No suc site with sid = " + siteId.toString());
    }

    /**
     * 管理员添加网站  同时添加 site 和 country 关联
     *
     * @param sites
     * @return List<Site>
     */
    @PostMapping("")
    @ApiOperation(value = "添加网站", notes = "添加网站时需要传入tile、url、icon、cateId等必填字段，其中，cateId要求必须存在对应的分类，否则会添加失败噢")
    @RequiresPermissions(PermisConstant.ADD_SITE)
    public Result<List<Site>> addSites(@Valid @RequestBody List<SiteAddVo> sites, @ApiIgnore @CurrentAdmin Admin admin) {
        List<Site> insertedSiteList;
        try {
            insertedSiteList = siteService.addSitesByAdmin(sites, admin.getAid());
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
        return Result.ok(insertedSiteList);
    }

    @PostMapping("{siteId}/countries")
    @ApiOperation("添加网站所适用的国家列表，不可重复添加")
    @RequiresPermissions(PermisConstant.MODIFY_SITE)
    public Result<Boolean> bindSiteToCountries(@PathVariable Integer siteId, @RequestBody List<String> countryCodes) {
        return countriesSitesService.AddRelations(siteId, countryCodes) > 0 ? Result.ok(true) : Result.ok((false));
    }

    @DeleteMapping("{siteId}/countries")
    @ApiOperation("移除网站所适用的国家列表")
    @RequiresPermissions(PermisConstant.MODIFY_SITE)
    public Result<Boolean> removeSiteFromCountries(@PathVariable Integer siteId, @RequestBody List<String> countryCodes) {
        return countriesSitesService.removeRelations(siteId, countryCodes) > 0 ? Result.ok(true) : Result.ok(false);
    }

    @PutMapping("{siteId}/state")
    @ApiOperation("审核更新网站的状态state字段，0为审核中、1为审核通过网站正常访问，2为屏蔽网站")
    @RequiresPermissions(PermisConstant.REVIEW_SITE)
    public Result<Boolean> updateSiteState(@PathVariable Integer siteId, @RequestParam @Min(0) @Max(2) Short state) {
        Site site = new Site();
        site.setSid(siteId);
        site.setState(state);
        return siteService.update(site) ? Result.ok(true) : Result.error();
    }

    @PutMapping("{siteId}/cateId")
    @ApiOperation("更新网站所属分类")
    @RequiresPermissions(PermisConstant.MODIFY_SITE)
    public Result<Boolean> updateCateId(@PathVariable Integer siteId, @RequestParam Integer cateId) {
        Site site = new Site();
        site.setSid(siteId);
        site.setCateId(cateId);
        SiteCategory category = siteCategoryService.getCateById(cateId);
        if (category == null) {
            return Result.error(ResultCode.NOT_FOUND.code(), "No such category with ID = " + cateId.toString());
        }
        return siteService.update(site) ? Result.ok(true) : Result.error();
    }

    @PutMapping("{siteId}")
    @ApiOperation(value = "更新一条网站数据的普通字段，返回更新后的记录", notes = "该接口仅能更新以下字段：title、icon、url，marks、weight")
    @RequiresPermissions(PermisConstant.MODIFY_SITE)
    public Result<Site> update(@PathVariable Integer siteId, @ApiParam("待更新的网站记录") @RequestBody Site site) {
        Site updateSite = Site.builder().title(site.getTitle()).icon(site.getIcon()).url(site.getUrl())
                            .marks(site.getMarks()).weight(site.getWeight()).build();
        updateSite.setSid(siteId);
        return siteService.update(updateSite) ? Result.ok(updateSite) : Result.error();
    }

    @DeleteMapping("{siteId}")
    @ApiOperation("删除一条网站记录")
    @RequiresPermissions(PermisConstant.DELETE_SITE)
    public Result<Boolean> delete(@PathVariable Integer siteId) {
        Site site = siteService.getSiteById(siteId);
        if (site == null) { return Result.error(ResultCode.NOT_FOUND.code(), "No such site with ID = " + siteId.toString()); }
        return siteService.deleteById(siteId) ? Result.ok(true) : Result.error();
    }

    @DeleteMapping("list")
    @ApiOperation("删除多条网站记录")
    @RequiresPermissions(PermisConstant.DELETE_SITE)
    public Result<Boolean> delete(@PathVariable Integer sid, @RequestBody List<Integer> siteIds) {
        return siteService.deleteSitesByIds(siteIds) > 0 ? Result.ok(true) : Result.error();
    }
}
