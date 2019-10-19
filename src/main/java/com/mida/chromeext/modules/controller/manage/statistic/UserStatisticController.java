package com.mida.chromeext.modules.controller.manage.statistic;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mida.chromeext.components.shiro.PermisConstant;
import com.mida.chromeext.modules.service.UserService;
import com.mida.chromeext.modules.vo.statistic.StatisticUserByCountry;
import com.mida.chromeext.utils.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author lihaoyu
 * @date 2019/10/19 14:52
 */
@RestController
@RequestMapping("manage/statistics/users")
@RequiresPermissions(PermisConstant.STATISTICS_USER)
@Api(value = "后台统计用户相关接口", tags = "提供用户统计相关的 Rest API")
public class UserStatisticController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "每日登录人数统计", notes = "分页方式查询获取网站，包含网站关联分类和创建网站的管理员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginDate", value = "开始时间  格式 yyyy-mm-dd", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束时间  格式 yyyy-mm-dd",  dataType = "String", paramType = "query"),})
    @GetMapping("daily")
    public Result<Map<String,Long>> listCountByDaily(@DateTimeFormat(pattern="yyyy-MM-dd") Date beginDate,
                                                @DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required = false) Date endDate){
        Map<String, Long> res = userService.listDailyCounts(beginDate, endDate);
        return Result.ok(res);
    }


    @ApiOperation(value = "每月登录人数统计", notes = "分页方式查询获取网站，包含网站关联分类和创建网站的管理员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginDate", value = "开始时间  格式 yyyy-mm-dd", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束时间  格式 yyyy-mm-dd",  dataType = "String", paramType = "query"),})
    @GetMapping("monthly")
    public Result<Map<String,Long>> listCountByMonthly(@DateTimeFormat(pattern="yyyy-MM-dd") Date beginDate,
                                             @DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required = false) Date endDate){
        Map<String, Long> res = userService.listMonthlyCounts(beginDate, endDate);
        return Result.ok(res);
    }

    @ApiOperation(value = "每个国家的用户数", notes = "分页方式查询获取网站，包含网站关联分类和创建网站的管理员信息")
    @GetMapping("country")
    public Result<List<StatisticUserByCountry>> listCountByCountry(){
        List<StatisticUserByCountry> res = userService.listCountsByCountry();
        return Result.ok(res);
    }

    @ApiOperation(value = "每个国家的用户数")
    @GetMapping
    public Result<Long> CountAllUser(){
       Long count = userService.countAll();
        return Result.ok(count);
    }
}
