package net.tabplus.api.modules.controller.manage.statistic;

import net.tabplus.api.components.shiro.PermisConstant;
import net.tabplus.api.modules.service.CountriesSitesService;
import net.tabplus.api.modules.vo.statistic.CountrySitesCount;
import net.tabplus.api.modules.service.SiteService;
import net.tabplus.api.modules.vo.statistic.CategorySitesCount;
import net.tabplus.api.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Result<Map<String, Long>> allSitesCount() {
        /**
         * 用户创建的网站总数
         */
        Long userSitesCount = siteService.countUserSites();
        /**
         * 系统（管理员）创建的网站总数
         */
        Long sysSitesCount = siteService.countSysSites();
        Long totalCount = userSitesCount + sysSitesCount;
        Map<String, Long> returnMap = new HashMap();
        returnMap.put("totalCount", totalCount);
        returnMap.put("sysSitesCount", sysSitesCount);
        returnMap.put("userSitesCount", userSitesCount);
        return Result.ok(returnMap);
    }

    @GetMapping("hot_sites")
    @ApiOperation(value = "实时热门站点")
    public Result<List<Map>> hotSites() {
        return Result.ok(siteService.hotSites());
    }
}
