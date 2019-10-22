package com.mida.chromeext.modules.controller.app;

import com.google.common.collect.Lists;
import com.mida.chromeext.annotation.CurrentUser;
import com.mida.chromeext.annotation.LoginRequired;
import com.mida.chromeext.modules.pojo.Site;
import com.mida.chromeext.modules.pojo.User;
import com.mida.chromeext.modules.service.SiteService;
import com.mida.chromeext.modules.vo.ListQueryVo;
import com.mida.chromeext.modules.vo.SiteAddVo;
import com.mida.chromeext.modules.vo.SiteListQueryVo;
import com.mida.chromeext.utils.Constant;
import com.mida.chromeext.utils.LocaleHelper;
import com.mida.chromeext.utils.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContextUtils;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * @author lihaoyu
 * @date 2019/9/24 18:50
 */
@RestController
@RequestMapping("sites")
@Api(value = "前台网站相关接口", tags = "提供网站site相关的 Rest API")
@Validated
@Slf4j
public class SitesController {
    @Autowired
    SiteService siteService;

    @GetMapping
    @ApiOperation(value = "网站获取接口", notes = "根据条件查询站点列表，为防止爬虫，前台站点接口需要传入国家(地区)码列表和网站分类ID列表，并限制单次访问最高返回100条数据量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "网站名称关键字", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "creatorType", value = "网站创建者类型，0表示由管理员创建，1表示由用户创建", dataType = "Integer", paramType = "query"),
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

        /**
         * 强制设置搜索条件，屏蔽不允许前端搜索的字段
         */
        queryVo.setDefaultConsForClient();

        List<Site> sites = siteService.queryList(queryVo);
        return Result.ok(sites);
    }

    @GetMapping("popular")
    @ApiOperation(value = "获取热门站点接口", notes = "需要指定国家（地区）码，默认自动获取")
    public Result<List<Site>> getPopularSites(@RequestParam(required = false) @ApiParam("当前分页") Integer pageNum,
                                              @RequestParam(required = false) @ApiParam("每页数据量") Integer pageSize,
                                              @RequestParam String countryCode, HttpServletRequest request) {
        if (StringUtils.isEmpty(countryCode)) { countryCode = LocaleHelper.getContextCountryCode(request); }
        SiteListQueryVo queryVo = new SiteListQueryVo();
        queryVo.setPageNum(pageNum);
        queryVo.setPageSize(pageSize);
        queryVo.setCountryCodes(Lists.newArrayList(countryCode));
        return Result.ok(siteService.queryList(queryVo));
    }

    @PostMapping("")
    @LoginRequired
    @ApiOperation("由用户添加网站，要求用户必须登录后才能添加自定义网站到系统上，且添加的网站必须通过审核后才能被其他用户搜索到")
    public Result<Site> create(@ApiIgnore @Validated SiteAddVo site, @ApiIgnore @CurrentUser User user) {
        try {
            Site newSite = siteService.addOneSiteByUser(site, user);
            return newSite.getSid() != null ? Result.ok(newSite) : Result.error();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }
}


