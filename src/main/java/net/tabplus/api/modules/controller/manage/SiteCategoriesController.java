package net.tabplus.api.modules.controller.manage;

import net.tabplus.api.components.shiro.PermisConstant;
import net.tabplus.api.modules.pojo.SiteCategory;
import net.tabplus.api.modules.service.SiteCategoryService;
import net.tabplus.api.modules.vo.ListResultVo;
import net.tabplus.api.utils.Result;
import net.tabplus.api.utils.ResultCode;
import net.tabplus.api.modules.vo.ListQueryVo;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
    @ApiOperation(value = "获取网站分类列表", notes = "当传入pageSize=-1时将默认返回所有分类数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页数，最小为1", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数据量，最大为100", required = true, dataType = "Integer", paramType = "query"),})
    @RequiresPermissions(PermisConstant.SHOW_SITE_CATEGORY)
    public Result<ListResultVo<SiteCategory>> getCategories(@ApiIgnore ListQueryVo queryVo) {
        if (queryVo.getPageSize() == -1) {
            queryVo.setPageNum(1);
            queryVo.setPageSize(0);
        }
        return Result.ok(siteCategoryService.listCategories(queryVo));
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
    public Result<Integer> delete(@ApiParam("网站分类id数组") @RequestBody List<Integer> ids) {
        int affectedRows = siteCategoryService.batchDelete(ids);
        return affectedRows > 0 ? Result.ok(affectedRows) : Result.error();
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
