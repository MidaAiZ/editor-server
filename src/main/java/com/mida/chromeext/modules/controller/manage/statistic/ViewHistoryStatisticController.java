package com.mida.chromeext.modules.controller.manage.statistic;

import com.mida.chromeext.components.shiro.PermisConstant;
import com.mida.chromeext.modules.service.SiteViewHistoryService;
import com.mida.chromeext.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("manage/statistics")
@RequiresPermissions(PermisConstant.STATISTICS_SITE_VIEW_HISTORY)
@Api(value = "后台统计站点浏览数据的接口", tags = "{}")
public class ViewHistoryStatisticController {
    @Autowired
    private SiteViewHistoryService siteViewHistoryService;

    @GetMapping("daily_site_views_count")
    @ApiOperation(value = "获取站点浏览数据每日数据，单位日")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginDate", value = "开始时间  格式 yyyy-mm-dd", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束时间  格式 yyyy-mm-dd", dataType = "String", paramType = "query"),})
    public Result<Map<String, Long>> listCountByDaily(@DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
                                                      @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(required = false) Date endDate) {
        Map<String, Long> res = siteViewHistoryService.listDailyViewCount(beginDate, endDate);
        return Result.ok(res);
    }

    @GetMapping("monthly_site_views_count")
    @ApiOperation(value = "获取站点浏览数据每月数据，单位月")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginDate", value = "开始时间  格式 yyyy-mm", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束时间  格式 yyyy-mm", dataType = "String", paramType = "query"),})
    public Result<Map<String, Long>> listCountByMonthly(@DateTimeFormat(pattern = "yyyy-MM") Date beginDate,
                                                        @DateTimeFormat(pattern = "yyyy-MM") @RequestParam(required = false) Date endDate) {
        Map<String, Long> res = siteViewHistoryService.listMonthlyViewCount(beginDate, endDate);
        return Result.ok(res);
    }

    @GetMapping("site_views_count")
    @ApiOperation(value = "统计系统网站总数")
    public Result<Long> allViewsCount() {
        Long count = siteViewHistoryService.getAllViewsCount();
        return Result.ok(count);
    }
}
