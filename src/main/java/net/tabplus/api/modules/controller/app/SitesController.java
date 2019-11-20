package net.tabplus.api.modules.controller.app;

import com.google.common.collect.Lists;
import net.tabplus.api.annotation.CurrentUser;
import net.tabplus.api.annotation.LoginRequired;
import net.tabplus.api.utils.Constant;
import net.tabplus.api.utils.Result;
import net.tabplus.api.modules.pojo.Site;
import net.tabplus.api.modules.pojo.User;
import net.tabplus.api.modules.service.SiteService;
import net.tabplus.api.modules.vo.SiteAddVo;
import net.tabplus.api.modules.vo.SiteListQueryVo;
import net.tabplus.api.utils.LocaleHelper;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

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
        // 设置前端筛选条件，keyword和category_id至少有一个不为空
        if (StringUtils.isEmpty(queryVo.getKeyword()) &&
                (queryVo.getCategoryIds() == null || queryVo.getCategoryIds().isEmpty())) {
            queryVo.setCategoryIds(Lists.newArrayList(0));
        }
        // 强制设置搜索条件，屏蔽不允许前端搜索的字段
        queryVo.setDefaultConsForClient();

        List<Site> sites = siteService.queryList(queryVo);
        return Result.ok(sites);
    }

    @GetMapping("popular")
    @ApiOperation(value = "获取热门站点接口", notes = "需要指定国家（地区）码，默认自动获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页数，最小为1", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数据量，最大为100", required = true, dataType = "Integer", paramType = "query"),})
    public Result<List<Site>> getPopularSites(@ApiParam("当前页数") @Min(1) @RequestParam(required = false) Integer pageNum,
                                              @ApiParam("每页数据量") @Max(100) @RequestParam(required = false) Integer pageSize,
                                              @RequestParam(required = false) String countryCode, HttpServletRequest request) {
        SiteListQueryVo vo = new SiteListQueryVo();
        if (pageNum != null && pageNum > 0) {
            vo.setPageNum(pageNum);
        }
        if (pageSize != null && pageSize > 0) {
            vo.setPageSize(pageSize);
        }
        if (StringUtils.isEmpty(countryCode)) {
            countryCode = LocaleHelper.getContextCountryCode(request);
        }
        vo.setCountryCodes(Lists.newArrayList(countryCode));
        return Result.ok(siteService.queryList(vo));
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


