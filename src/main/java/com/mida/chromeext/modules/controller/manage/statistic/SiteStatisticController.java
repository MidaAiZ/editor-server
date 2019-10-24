package com.mida.chromeext.modules.controller.manage.statistic;

import com.mida.chromeext.components.shiro.PermisConstant;
import com.mida.chromeext.modules.service.CountriesSitesService;
import com.mida.chromeext.modules.service.SiteService;
import com.mida.chromeext.modules.vo.statistic.CategorySitesCount;
import com.mida.chromeext.modules.vo.statistic.CountrySitesCount;
import com.mida.chromeext.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/10/19 20:44
 */
@RestController
@RequestMapping("manage/statistics")
@RequiresPermissions(PermisConstant.STATISTICS_SITE)
@Api(value = "后台统计网站相关接口", tags = "提供网站统计相关的 Rest API")
public class SiteStatisticController {

    @Autowired
    CountriesSitesService countriesSitesService;

    @Autowired
    SiteService siteService;

    @GetMapping("country_sites_count")
    @ApiOperation(value = "统计每个国家的精准网站数")
    public Result<List<CountrySitesCount>> listCountByCountry() {
        List<CountrySitesCount> res = countriesSitesService.listSitesCountByCountry();
        return Result.ok(res);
    }

    @GetMapping("category_sites_count")
    @ApiOperation(value = "统计各个分类的精准网站数")
    public Result<List<CategorySitesCount>> listCountByCategory() {
        List<CategorySitesCount> res = siteService.listSitesCountByCategory();
        return Result.ok(res);
    }

    @GetMapping("sites_count")
    @ApiOperation(value = "统计系统网站总数")
    public Result<Long> allSitesCount() {
        Long count = siteService.countAll();
        return Result.ok(count);
    }
}
