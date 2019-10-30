package com.mida.chromeext.modules.controller.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mida.chromeext.modules.dto.SiteTrackerItemDto;
import com.mida.chromeext.modules.pojo.SiteTracker;
import com.mida.chromeext.modules.service.SiteTrackerService;
import com.mida.chromeext.utils.LocaleHelper;
import com.mida.chromeext.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "前台用户流量跟踪操作", tags = "{}")
@RequestMapping("site_trackers")
public class SiteTrackersController {
    @Autowired
    private SiteTrackerService siteTrackerService;

    @GetMapping("default")
    @ApiOperation(value = "获取用户所属地区的默认流量跟踪器", notes = "返回流量跟踪键值对，通过加在访问url后缀上以获取广告方流量追踪")
    public Result<List<SiteTrackerItemDto>> getDefault(@RequestParam(required = false) @ApiParam("地区码,可选，未传入时系统自动判断") String code, HttpServletRequest request) {
        if (StringUtils.isEmpty(code)) {
            code = LocaleHelper.getContextCountryCode(request);
        }
        SiteTracker record = siteTrackerService.getOneByCountryCode(code);
        if (record != null) {
            return Result.ok(JSONObject.parseArray(record.getTrackers(), SiteTrackerItemDto.class));
        } else {
            return Result.ok(new ArrayList());
        }
    }
}
