package com.mida.chromeext.modules.controller.manage;

import com.mida.chromeext.modules.pojo.SiteCategory;
import com.mida.chromeext.modules.service.SiteCategoryService;
import com.mida.chromeext.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.mida.chromeext.components.shiro.PermisConstant;


import javax.validation.Valid;
import java.util.List;

@RestController("mngSiteCategoriesController")
@RequestMapping("manage/site_categories")
@Api(value = "后台网站分类操作接口", tags = "{}")
@Validated
public class SiteCategoriesController {
    @Autowired
    private SiteCategoryService siteCategoryService;

    @GetMapping
    @ApiOperation("获取网站分类列表")
    @RequiresPermissions(PermisConstant.SHOW_SITE_CATEGORY)
    public Result<List<SiteCategory>> listAllCategories() {
        List<SiteCategory> siteCategories = siteCategoryService.listAllCategories();
        return Result.ok(siteCategories);
    }

    @PostMapping
    @ApiOperation("添加网站分类，需要管理员操作")
    @RequiresPermissions(PermisConstant.ADD_SITE_CATEGORY)
    public Result<List<SiteCategory>> addCategories(@Valid @RequestBody List<SiteCategory> categories) {
        // 搭配类上的@Validated才能做List嵌套验证
        List<SiteCategory> siteCategories = siteCategoryService.addCategories(categories);
        if (siteCategories == null) {
            return Result.error("添加的种类已存在");
        }
        return Result.ok(siteCategories);
    }

    @DeleteMapping("")
    @ApiOperation("删除网站分类，需要管理员操作")
    @RequiresPermissions(PermisConstant.DELETE_SITE_CATEGORY)
    public Result<Integer> delete(@ApiParam("种类id数组") @RequestBody List<Integer> ids) {
        // todo 管理员权限          关联太多，应该禁止删除
        int affectedRows = siteCategoryService.batchDelete(ids);
        return Result.ok(affectedRows);
    }

    @PutMapping("")
    @ApiOperation("单个修改网站分类，需要管理员操作")
    @RequiresPermissions(PermisConstant.MODIFY_SITE_CATEGORY)
    public Result<SiteCategory> update(@Valid @RequestBody SiteCategory categories) {
        // todo 管理员权限
        siteCategoryService.updateCategories(categories);
        return Result.ok(categories);
    }
}
