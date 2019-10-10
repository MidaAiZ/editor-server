package com.mida.chromeext.controller;

import com.mida.chromeext.pojo.SiteViewHistory;
import com.mida.chromeext.service.SiteViewHistoryService;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.vo.SiteViewHistoryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("view_histories")
@Api(value = "网站浏览历史记录", tags = "{}")
@Validated
public class ViewHistoriesController {
    @Autowired
    private SiteViewHistoryService siteViewHistoryService;

    @PostMapping("")
    @ApiOperation(value = "添加1个新的浏览记录", notes = "每当用户访问新的网址时请求此接口")
    public Result<Boolean> create(@Validated @ApiParam("浏览记录") @RequestBody SiteViewHistory history, HttpServletRequest request) {
        siteViewHistoryService.create(history, request);
        return Result.ok(true);
    }

    // TODO 添加管理员权限
    @PostMapping("list")
    @ApiOperation(value = "获取网站浏览记录接口", notes = "管理员操作，需要权限，注意，此接口为POST接口，传入json格式数据(由于数据结构过于复杂，此接口POST请求方式不遵循rest规范)")
    public Result<List<SiteViewHistory>> listSitesByPage(@Validated @ApiParam("查询参数") @RequestBody SiteViewHistoryVo queryVo) {
        List<SiteViewHistory> histories = siteViewHistoryService.getList(queryVo);
        return Result.ok(histories);
    }
}
