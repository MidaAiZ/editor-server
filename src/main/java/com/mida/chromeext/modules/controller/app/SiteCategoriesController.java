package com.mida.chromeext.modules.controller.app;

import com.mida.chromeext.modules.pojo.SiteCategory;
import com.mida.chromeext.modules.service.SiteCategoryService;
import com.mida.chromeext.modules.vo.ListQueryVo;
import com.mida.chromeext.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("site_categories")
@Api(value = "前台网站分类操作获取接口", tags = "{}")
@Validated
public class SiteCategoriesController {
    @Autowired
    private SiteCategoryService siteCategoryService;

    @GetMapping("")
    @ApiOperation("获取网站分类列表")
    public Result<List<SiteCategory>> getCategories(@ApiParam("当前页数") @Min(1) @RequestParam(required = false) Integer pageNum, @ApiParam("每页数据量") @Max(100) @RequestParam(required = false) Integer pageSize) {
        ListQueryVo queryVo = new ListQueryVo();
        if (pageNum != null && pageNum > 0) { queryVo.setPageNum(pageNum); }
        if (pageSize != null && pageSize > 0) { queryVo.setPageSize(pageSize); }
        List<SiteCategory> siteCategories = siteCategoryService.listCategories(queryVo);
        return Result.ok(siteCategories);
    }
}
