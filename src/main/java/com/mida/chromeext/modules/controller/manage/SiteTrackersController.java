package com.mida.chromeext.modules.controller.manage;

import com.alibaba.fastjson.JSONObject;
import com.mida.chromeext.components.shiro.PermisConstant;
import com.mida.chromeext.modules.dto.SiteTrackerItemDto;
import com.mida.chromeext.modules.pojo.SiteTracker;
import com.mida.chromeext.modules.service.SiteTrackerService;
import com.mida.chromeext.utils.ObjectToMap;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController("mngSiteTrackersController")
@Api(value = "后台用户流量跟踪操作", tags = "{}")
@RequestMapping("manage/site_trackers")
public class SiteTrackersController {
    @Autowired
    private SiteTrackerService siteTrackerService;

    @GetMapping("")
    @ApiOperation("获取流量追踪器列表，需要管理员权限")
    @RequiresPermissions(PermisConstant.SHOW_SITE_TRACKER)
    public Result<List<SiteTracker>> getList() {
        return Result.ok(siteTrackerService.getAllList());
    }

    @GetMapping("{code}")
    @ApiOperation("获取一条获取流量追踪器记录，需要管理员权限")
    @RequiresPermissions(PermisConstant.SHOW_SITE_TRACKER)
    public Result<Map> getOne(@PathVariable String code) {
        SiteTracker record = siteTrackerService.getOneByCountryCode(code);
        if (record == null) {
            return Result.error(ResultCode.NOT_FOUND.code(), "No such siteTracker with code = " + code);
        }
        try {
            Map returnMap = ObjectToMap.convert(record);
            returnMap.put("trackers", JSONObject.parseArray(record.getTrackers(), SiteTrackerItemDto.class));
            return Result.ok(returnMap);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("{code}")
    @ApiOperation("新建流量追踪器列表，返回新建成功的列表，需要管理员权限")
    @RequiresPermissions(PermisConstant.ADD_SITE_TRACKER)
    public Result<SiteTracker> createList(@PathVariable String code, @RequestBody @ApiParam("新建的流量追踪器数组") List<@Valid SiteTrackerItemDto> items) {
        SiteTracker record = new SiteTracker();
        record.setCountryCode(code);
        record.setTrackers(JSONObject.toJSONString(items));
        try {
           return siteTrackerService.createOne(record) ? Result.ok(record) : Result.error();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PutMapping("{code}")
    @ApiOperation("更新流量追踪器列表，返回更新后的列表，需要管理员权限")
    @RequiresPermissions(PermisConstant.MODIFY_SITE_TRACKER)
    public Result<SiteTracker> updateList(@PathVariable String code, @RequestBody @ApiParam("更新的默认菜单配置数组，必须包含主键did") List<@Valid SiteTrackerItemDto> items) {
        SiteTracker record = new SiteTracker();
        record.setCountryCode(code);
        record.setTrackers(JSONObject.toJSONString(items));
        try {
            return siteTrackerService.updateByCountryCode(record) ? Result.ok(record) : Result.error();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping("")
    @ApiOperation("批量流量追踪器列表，返回删除成功的数量，需要管理员权限")
    @RequiresPermissions(PermisConstant.DELETE_SITE_TRACKER)
    public Result<Integer> deleteList(@RequestBody @ApiParam("需要删除的默认列表的did数组") List<Integer> idList) {
        try {
            int count = siteTrackerService.deleteList(idList);
            return count == idList.size() ? Result.ok(count) : Result.error();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
