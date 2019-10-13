package com.mida.chromeext.modules.controller.manage;

import com.mida.chromeext.components.shiro.PermisConstant;
import com.mida.chromeext.modules.dto.SearchEngineAddDto;
import com.mida.chromeext.modules.pojo.SearchEngine;
import com.mida.chromeext.modules.service.SearchEngineService;
import com.mida.chromeext.utils.NumConst;
import com.mida.chromeext.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@RestController("mngSearchEnginesController")
@RequestMapping("manage/search-engines")
@Api(value = "后台默认搜索引擎配置操作", tags = "{}")
public class SearchEnginesController {

    @Autowired
    SearchEngineService searchEngineService;

    @GetMapping("default")
    @ApiOperation(value = "通过唯一国家码获取搜索引擎，如果指定的配置不存在，获取默认值", notes = "国家码可选，如果不传后端则根据请求体自动获取国家")
    @RequiresPermissions(PermisConstant.SHOW_SEARCH_ENGINE)
    public Result<SearchEngine> getSearchEngine(@ApiParam("国家码") @RequestParam(required = false) String code, HttpServletRequest request) {
        if (StringUtils.isEmpty(code)) {
            Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
            code = locale.getCountry();
        }
        SearchEngine searchEngine = searchEngineService.getSearchEngine(code);
        return Result.ok(searchEngine);
    }

    @GetMapping("")
    @ApiOperation(value = "获取所有国家的搜索引擎,需要管理员权限")
    @RequiresPermissions(PermisConstant.SHOW_SEARCH_ENGINE)
    public Result<List<SearchEngine>> listAllSearchEngine() {
        return Result.ok(searchEngineService.listAllSearchEngine());
    }

    @PostMapping("")
    @ApiOperation(value = "添加某个国家的搜索引擎,需要管理员权限")
    @RequiresPermissions(PermisConstant.ADD_SEARCH_ENGINE)
    public Result<SearchEngine> addSearchEngine(@ApiParam("必须包含所有字段") @RequestBody SearchEngineAddDto dto) {
        SearchEngine searchEngine = searchEngineService.addSearchEngine(dto);
        if (searchEngine == null) {
            return Result.error("已经存在，不能添加");
        }
        return Result.ok(searchEngine);
    }

    @DeleteMapping
    @ApiOperation(value = "删除某个国家的搜索引擎,需要管理员权限")
    @RequiresPermissions(PermisConstant.DELETE_SEARCH_ENGINE)
    public Result addSearchEngine(@ApiParam("国家码") String code) {
        int affectRow = searchEngineService.deleteSearchEngine(code);
        if (affectRow == NumConst.NUM1) {
            return Result.ok("删除成功");
        }
        return Result.error("不存在，删除失败");
    }

    @PutMapping
    @ApiOperation(value = "修改某个国家的搜索引擎,需要管理员权限")
    @RequiresPermissions(PermisConstant.MODIFY_SEARCH_ENGINE)
    public Result<SearchEngine> updateSearchEngine(@ApiParam("必须包含所有字段") @RequestBody SearchEngineAddDto dto) {
        int deleteNumber = searchEngineService.deleteSearchEngine(dto.getCountryCode());
        if (deleteNumber != NumConst.NUM1) {
            return Result.error("不存在，修改失败");
        }
        SearchEngine searchEngine = searchEngineService.addSearchEngine(dto);
        if (searchEngine == null) {
            return Result.error("修改失败");
        }
        return Result.ok(searchEngine);
    }

}
