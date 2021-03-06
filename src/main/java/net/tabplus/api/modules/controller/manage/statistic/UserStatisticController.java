package net.tabplus.api.modules.controller.manage.statistic;

import net.tabplus.api.components.shiro.PermisConstant;
import net.tabplus.api.modules.service.UserService;
import net.tabplus.api.modules.vo.statistic.CountryUsersCount;
import net.tabplus.api.utils.Result;
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
import java.util.List;
import java.util.Map;

/**
 * @author lihaoyu
 * @date 2019/10/19 14:52
 */
@RestController
@RequestMapping("manage/statistics")
@RequiresPermissions(PermisConstant.STATISTICS_USER)
@Api(value = "后台统计用户相关接口", tags = "提供用户统计相关的 Rest API")
public class UserStatisticController {

    @Autowired
    UserService userService;

    @GetMapping("daily_alive_users_count")
    @ApiOperation(value = "日活，每日登录人数统计", notes = "统计方式不局限于已注册用户，只要每一个独立的客户端（无论用户是否已登录）访问过API，即算作一个日活")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginDate", value = "开始时间  格式 yyyy-mm-dd", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束时间  格式 yyyy-mm-dd", dataType = "String", paramType = "query"),})
    public Result<Map<String, Long>> listCountByDaily(@DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
                                                      @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(required = false) Date endDate) {
        Map<String, Long> res = userService.listDailyAliveUsersCount(beginDate, endDate);
        return Result.ok(res);
    }


    @GetMapping("daily_new_users_count")
    @ApiOperation(value = "获取每日注册的新用户数量", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginDate", value = "开始时间  格式 yyyy-mm-dd", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束时间  格式 yyyy-mm-dd", dataType = "String", paramType = "query"),})
    public Result<Map<String, Long>> dailyNewUsersCount(@DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
                                                        @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(required = false) Date endDate) {
        Map<String, Long> res = userService.listDailyNewUsersCount(beginDate, endDate);
        return Result.ok(res);
    }

    @GetMapping("monthly_alive_users_count")
    @ApiOperation(value = "月活，每月登录人数统计", notes = "统计方式不局限于已注册用户，只要每一个独立的客户端（无论用户是否已登录）访问过API，即算作一个月活")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginDate", value = "开始时间  格式 yyyy-mm", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束时间  格式 yyyy-mm", dataType = "String", paramType = "query"),})
    public Result<Map<String, Long>> listCountByMonthly(@DateTimeFormat(pattern = "yyyy-MM") Date beginDate,
                                                        @DateTimeFormat(pattern = "yyyy-MM") @RequestParam(required = false) Date endDate) {
        Map<String, Long> res = userService.listMonthlyAliveUsersCount(beginDate, endDate);
        return Result.ok(res);
    }

    @ApiOperation(value = "统计各个国家的用户数")
    @GetMapping("country_users_count")
    public Result<List<CountryUsersCount>> listCountByCountry() {
        List<CountryUsersCount> res = userService.listUsersCountByCountry();
        return Result.ok(res);
    }

    @GetMapping("users_count")
    @ApiOperation(value = "统计系统用户总数")
    public Result<Long> allUsersCount() {
        Long count = userService.getAllUsersCount();
        return Result.ok(count);
    }
}
