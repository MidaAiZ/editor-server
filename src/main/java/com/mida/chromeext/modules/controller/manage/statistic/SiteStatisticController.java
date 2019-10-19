package com.mida.chromeext.modules.controller.manage.statistic;

import java.util.List;

import com.mida.chromeext.modules.service.SiteService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mida.chromeext.components.shiro.PermisConstant;
import com.mida.chromeext.modules.service.CountrySiteService;
import com.mida.chromeext.modules.vo.statistic.StatisticSiteByCategory;
import com.mida.chromeext.modules.vo.statistic.StatisticSiteByCountry;
import com.mida.chromeext.utils.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author lihaoyu
 * @date 2019/10/19 20:44
 */
@RestController
@RequestMapping("manage/statistics/sites")
@RequiresPermissions(PermisConstant.STATISTICS_SITE)
@Api(value = "后台统计网站相关接口", tags = "提供网站统计相关的 Rest API")
public class SiteStatisticController {

    @Autowired
    CountrySiteService countrySiteService;

    @Autowired
    SiteService siteService;

    @ApiOperation(value = "每个国家的网站数")
    @GetMapping("country")
    public Result<List<StatisticSiteByCountry>> listCountByCountry(){
        List<StatisticSiteByCountry> res
                = countrySiteService.listCountByCountry();
        return Result.ok(res);
    }

    @ApiOperation(value = "每个分类的网站数")
    @GetMapping("category")
    public Result<List<StatisticSiteByCategory>> listCountByCategory(){
        List<StatisticSiteByCategory> res
                = siteService.listCountByCategory();
        return Result.ok(res);
    }

    @ApiOperation(value = "网站总数")
    @GetMapping
    public Result<Long> listAllSite(){
        Long count = siteService.countAll();
        return Result.ok(count);
    }
}
