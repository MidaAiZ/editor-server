package com.mida.chromeext.modules.controller.manage;

import com.mida.chromeext.components.shiro.PermisConstant;
import com.mida.chromeext.modules.pojo.SiteCategory;
import com.mida.chromeext.modules.service.SiteCategoryService;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation("添加网站分类，需要管理员权限")
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
    @ApiOperation("删除多个网站分类，返回成功删除的数量，仅能删除分类下没有关联网站的记录")
    @RequiresPermissions(PermisConstant.DELETE_SITE_CATEGORY)
    public Result<Integer> delete(@ApiParam("种类id数组") @RequestBody List<Integer> ids) {
        int affectedRows = siteCategoryService.batchDelete(ids);
        return Result.ok(affectedRows);
    }

    @DeleteMapping("{cid}")
    @ApiOperation("删除一个网站分类，返回删除结果（true || false），仅能删除没有关联网站的分类")
    @RequiresPermissions(PermisConstant.DELETE_SITE_CATEGORY)
    public Result<Boolean> deleteById(@PathVariable Integer cid) {
        SiteCategory siteCategory = siteCategoryService.getCateById(cid);
        if (siteCategory == null) {
            return Result.error(ResultCode.NOT_FOUND.code(), "No such category with ID = " + cid.toString());
        }
        return siteCategoryService.deleteById(cid) ? Result.ok(true) : Result.error("The category is not allowed to be deleted cause there is an existing website association");
    }

    @PutMapping("")
    @ApiOperation("修改多个网站分类")
    @RequiresPermissions(PermisConstant.MODIFY_SITE_CATEGORY)
    public Result<List<SiteCategory>> updateList(@Valid @RequestBody List<SiteCategory> categories) {
        siteCategoryService.updateCategories(categories);
        return Result.ok(categories);
    }

    @PutMapping("{cid}")
    @ApiOperation("修改1个网站分类")
    @RequiresPermissions(PermisConstant.MODIFY_SITE_CATEGORY)
    public Result<Boolean> updateOne(@PathVariable Integer cid, @Valid @RequestBody SiteCategory category) {
        category.setCid(cid);
        return siteCategoryService.updateOneCategory(category) ? Result.ok(true) : Result.error();
    }
}
