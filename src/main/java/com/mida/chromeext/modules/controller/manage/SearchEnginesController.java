package com.mida.chromeext.modules.controller.manage;

import com.alibaba.fastjson.JSONObject;
import com.mida.chromeext.components.shiro.PermisConstant;
import com.mida.chromeext.modules.dto.SearchEngineItemDto;
import com.mida.chromeext.modules.dto.SearchEngineAddDto;
import com.mida.chromeext.modules.pojo.SearchEngine;
import com.mida.chromeext.modules.service.SearchEngineService;
import com.mida.chromeext.utils.NumConst;
import com.mida.chromeext.utils.ObjectToMap;
import com.mida.chromeext.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController("mngSearchEnginesController")
@RequestMapping("manage/search-engines")
@Api(value = "后台默认搜索引擎配置操作", tags = "{}")
@Validated
public class SearchEnginesController {

    @Autowired
    SearchEngineService searchEngineService;

    @GetMapping("")
    @ApiOperation(value = "获取所有国家的搜索引擎,需要管理员权限")
    @RequiresPermissions(PermisConstant.SHOW_SEARCH_ENGINE)
    public Result<List<SearchEngine>> listAllSearchEngine() {
        return Result.ok(searchEngineService.listAllSearchEngine());
    }

    @GetMapping("{code}")
    @ApiOperation(value = "通过唯一国家码获取搜索引擎")
    @RequiresPermissions(PermisConstant.SHOW_SEARCH_ENGINE)
    public Result<Map> getSearchEngine(@ApiParam("国家码") @PathVariable String code, HttpServletRequest request) {
        SearchEngine searchEngine = searchEngineService.getSearchEngine(code);
        if (searchEngine == null) {
            return Result.ok();
        }
        try {
            Map returnMap = ObjectToMap.convert(searchEngine);
            returnMap.put("engines", JSONObject.parseArray(searchEngine.getEngines(), SearchEngineItemDto.class));
            return Result.ok(returnMap);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("list")
    @ApiOperation(value = "批量添加某个国家的搜索引擎,需要管理员权限", notes = "返回新建的搜索引擎列表")
    @RequiresPermissions(PermisConstant.ADD_SEARCH_ENGINE)
    public Result<List<SearchEngine>> addSearchEngine(@ApiParam("必须包含所有字段") @RequestBody List<@Valid SearchEngineAddDto> engineDtoList) {
        return Result.ok(searchEngineService.addSearchEngineList(engineDtoList));
    }

    @DeleteMapping("{eid}")
    @ApiOperation(value = "删除某个国家的搜索引擎,需要管理员权限")
    @RequiresPermissions(PermisConstant.DELETE_SEARCH_ENGINE)
    public Result addSearchEngine(@ApiParam("主键eid") @PathVariable Integer eid) {
        return searchEngineService.deleteSearchEngine(eid) ? Result.ok(true) : Result.error();
    }

    @PutMapping("{eid}")
    @ApiOperation(value = "修改某个国家的搜索引擎,需要管理员权限")
    @RequiresPermissions(PermisConstant.MODIFY_SEARCH_ENGINE)
    public Result<SearchEngine> updateSearchEngine(@PathVariable Integer eid, @ApiParam("必须包含所有字段") @Valid @RequestBody SearchEngineAddDto engineDto) {
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.setEid(eid);
        searchEngine.setCountryCode(searchEngine.getCountryCode());
        searchEngine.setEngines(JSONObject.toJSONString(engineDto.getEngines()));
        return searchEngineService.updateSearchEngine(searchEngine) ? Result.ok(searchEngine) : Result.error();
    }

}
