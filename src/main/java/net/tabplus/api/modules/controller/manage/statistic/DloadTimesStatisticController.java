package net.tabplus.api.modules.controller.manage.statistic;

import net.tabplus.api.components.shiro.PermisConstant;
import net.tabplus.api.utils.Result;
import net.tabplus.api.modules.service.DownloadRecordService;
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

/**
 * 软件下载次数统计
 */
@RestController
@RequestMapping("manage/statistics")
@RequiresPermissions(PermisConstant.STATISTICS_DLOAD_RECORD)
@Api(value = "后台统计插件下载的相关接口", tags = "提供下载记录统计相关的 Rest API")
public class DloadTimesStatisticController {
    @Autowired
    private DownloadRecordService downloadRecordService;

    @GetMapping("daily_downloads_count")
    @ApiOperation(value = "获取插件下载记录统计每日数据，单位日")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginDate", value = "开始时间  格式 yyyy-mm-dd", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束时间  格式 yyyy-mm-dd", dataType = "String", paramType = "query"),})
    public Result<Map<String, Long>> listCountByDaily(@DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
                                                      @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(required = false) Date endDate) {
        Map<String, Long> res = downloadRecordService.listDailyDloadCount(beginDate, endDate);
        return Result.ok(res);
    }

    @GetMapping("monthly_downloads_count")
    @ApiOperation(value = "获取插件下载记录统计每月数据，单位月")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginDate", value = "开始时间  格式 yyyy-mm", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束时间  格式 yyyy-mm", dataType = "String", paramType = "query"),})
    public Result<Map<String, Long>> listCountByMonthly(@DateTimeFormat(pattern = "yyyy-MM") Date beginDate,
                                                        @DateTimeFormat(pattern = "yyyy-MM") @RequestParam(required = false) Date endDate) {
        Map<String, Long> res = downloadRecordService.listMonthlyDloadCount(beginDate, endDate);
        return Result.ok(res);
    }

    @GetMapping("downloads_count")
    @ApiOperation(value = "统计下载总数")
    public Result<Long> allViewsCount() {
        Long count = downloadRecordService.getAllDloadCount();
        return Result.ok(count);
    }
}
