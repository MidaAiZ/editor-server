package com.mida.chromeext.modules.controller.app;

import com.mida.chromeext.annotation.CurrentUser;
import com.mida.chromeext.modules.pojo.Site;
import com.mida.chromeext.modules.pojo.User;
import com.mida.chromeext.modules.service.SiteService;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.modules.vo.SiteAddVo;
import com.mida.chromeext.modules.vo.SiteListQueryVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/24 18:50
 */
@RestController
@RequestMapping("sites")
@Api(value = "网站相关接口", tags = "提供网站site相关的 Rest API")
@Validated
public class SitesController {
    @Autowired
    SiteService siteService;

    @PostMapping("list")
    @ApiOperation(value = "网站获取接口", notes = "分页方式获取网站，也可以全部获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyWord", value = "网站名称关键字", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "countryId", value = "国家id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "siteCategory", value = "网站类型对象", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "查询第几页，最小为1", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条，为0时查询全部数据", required = true, dataType = "Integer", paramType = "query"),})
    public List<Site> listSitesByPage(@RequestBody @ApiParam("分页查询参数") SiteListQueryVo queryVo) {
        List<Site> sites = siteService.listSitesByPage(queryVo);
        return sites;
    }
}


