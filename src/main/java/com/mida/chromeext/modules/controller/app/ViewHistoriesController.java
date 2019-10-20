package com.mida.chromeext.modules.controller.app;

import com.mida.chromeext.modules.pojo.SiteViewHistory;
import com.mida.chromeext.modules.service.SiteViewHistoryService;
import com.mida.chromeext.utils.Result;
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

@RestController
@RequestMapping("view_histories")
@Api(value = "前台网站浏览历史记录操作接口", tags = "{}")
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
}
