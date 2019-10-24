package com.mida.chromeext.modules.controller.manage;

import com.mida.chromeext.components.shiro.PermisConstant;
import com.mida.chromeext.modules.pojo.SiteViewHistory;
import com.mida.chromeext.modules.service.SiteViewHistoryService;
import com.mida.chromeext.modules.vo.SiteViewHistoryVo;
import com.mida.chromeext.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController("mngViewHistoriesController")
@RequestMapping("manage/view_histories")
public class ViewHistoriesController {
    @Autowired
    private SiteViewHistoryService siteViewHistoryService;

    @GetMapping("list")
    @ApiOperation(value = "获取网站浏览记录接口", notes = "管理员操作，需要权限，注意，此接口为POST接口，传入json格式数据(由于数据结构过于复杂，此接口POST请求方式不遵循rest规范)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "siteTitle", value = "网站名称，模糊查询", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "siteUrl", value = "网站url，模糊查询", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "siteUrlList", value = "网站url列表，精确查询", dataType = "String", allowMultiple = true, paramType = "query"),
            @ApiImplicitParam(name = "siteTitleList", value = "网站名称列表，精准查询", dataType = "Integer", allowMultiple = true, paramType = "query"),
            @ApiImplicitParam(name = "siteIdList", value = "网站id列表，精准查询", dataType = "Integer", allowMultiple = true, paramType = "query"),
            @ApiImplicitParam(name = "userIdList", value = "用户id列表，精准查询", dataType = "Integer", allowMultiple = true, paramType = "query"),
            @ApiImplicitParam(name = "ipList", value = "ip地址列表，精准查询", dataType = "String", allowMultiple = true, paramType = "query"),
            @ApiImplicitParam(name = "lastViewTimeBefore", value = "浏览时间<=，格式2019-01-01 00：00：00", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "lastViewTimeAfter", value = "浏览时间>=，格式2019-01-01 00：00：00", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数，最小为1", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数据量，最大为100", required = true, dataType = "Integer", paramType = "query"),})
    @RequiresPermissions(PermisConstant.SHOW_SITE_VIEW_HISTORY)
    public Result<List<SiteViewHistory>> listSitesByPage(@Validated @ApiIgnore @RequestParam SiteViewHistoryVo queryVo) {
        List<SiteViewHistory> histories = siteViewHistoryService.getList(queryVo);
        return Result.ok(histories);
    }
}
