package com.mida.chromeext.controller;

import java.util.List;
import java.util.Set;

import com.mida.chromeext.dao.SiteCategoryDAO;
import com.mida.chromeext.pojo.SiteCategory;
import com.mida.chromeext.pojo.SiteCategoryExample;
import com.mida.chromeext.service.SiteCategoryService;
import com.mida.chromeext.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mida.chromeext.annotation.CurrentUser;
import com.mida.chromeext.annotation.LoginRequired;
import com.mida.chromeext.pojo.User;
import com.mida.chromeext.pojo.UserMenu;
import com.mida.chromeext.service.UserMenuService;
import com.mida.chromeext.utils.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("site_categories")
@Api(tags = "网站分类操作接口")
@Validated
public class SiteCatoriesController {

    @Autowired
    private SiteCategoryService siteCategoryService;

    @GetMapping("")
    @ApiOperation("获取网站分类列表，屏蔽敏感字段")
    public Result<List<SiteCategory>> listAllCategories() {
        List<SiteCategory> siteCategories = siteCategoryService.listAllCategories();
        return Result.ok(siteCategories);
    }

    @PostMapping("")
    @ApiOperation("添加网站分类，需要管理员操作")
    public Result<List<SiteCategory>> addCategories(@Valid @RequestBody List<SiteCategory> categories) {
        // 搭配类上的@Validated才能做List嵌套验证
        // todo 管理员权限
        List<SiteCategory> siteCategories = siteCategoryService.addCategories(categories);
        if(siteCategories == null){
            return Result.error("添加的种类已存在");
        }
        return Result.ok(siteCategories);
    }

    @DeleteMapping("")
    @ApiOperation("删除网站分类，需要管理员操作")
    public Result<Integer> delete(@ApiParam("种类id数组") @RequestBody List<Integer> ids){
        // todo 管理员权限          关联太多，应该禁止删除
        int affectedRows = siteCategoryService.batchDelete(ids);
        return Result.ok(affectedRows);
    }

    @PutMapping("")
    @ApiOperation("单个修改网站分类，需要管理员操作")
    public Result<SiteCategory> update(@Valid @RequestBody SiteCategory categories){
        // todo 管理员权限
        siteCategoryService.updateCategories(categories);
        return Result.ok(categories);
    }

}
