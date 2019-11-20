package net.tabplus.api.modules.controller.manage;

import com.alibaba.fastjson.JSONObject;
import net.tabplus.api.annotation.CurrentAdmin;
import net.tabplus.api.components.shiro.PermisConstant;
import net.tabplus.api.modules.dto.SearchEngineAddDto;
import net.tabplus.api.modules.dto.SearchEngineItemDto;
import net.tabplus.api.modules.pojo.Admin;
import net.tabplus.api.modules.pojo.SearchEngine;
import net.tabplus.api.modules.service.SearchEngineService;
import net.tabplus.api.modules.vo.SeachEngineRelVo;
import net.tabplus.api.utils.ObjectToMap;
import net.tabplus.api.utils.Result;
import net.tabplus.api.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
    public Result<List<SeachEngineRelVo>> listAllSearchEngine() {
        return Result.ok(searchEngineService.listAllSearchEngine());
    }

    @GetMapping("{code}")
    @ApiOperation(value = "通过唯一国家码获取搜索引擎")
    @RequiresPermissions(PermisConstant.SHOW_SEARCH_ENGINE)
    public Result<Map> getSearchEngine(@ApiParam("国家码") @PathVariable String code, HttpServletRequest request) {
        SearchEngine searchEngine = searchEngineService.getSearchEngine(code);
        if (searchEngine == null) {
            return Result.error(ResultCode.NOT_FOUND.code(), "No such searchEngine with code = " + code);
        }
        try {
            Map returnMap = ObjectToMap.convert(searchEngine);
            returnMap.put("engines", JSONObject.parseArray(searchEngine.getEngines(), SearchEngineItemDto.class));
            return Result.ok(returnMap);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("{code}")
    @ApiOperation(value = "批量添加某个国家的搜索引擎,需要管理员权限", notes = "返回新建的搜索引擎列表")
    @RequiresPermissions(PermisConstant.ADD_SEARCH_ENGINE)
    public Result<SearchEngine> addSearchEngine(@PathVariable String code, @ApiParam("必须包含所有字段") @RequestBody @Valid SearchEngineAddDto engineDto, @ApiIgnore @CurrentAdmin Admin admin) {
        engineDto.setCountryCode(code);
        return Result.ok(searchEngineService.addSearchEngine(engineDto, admin));
    }


    @PutMapping("{code}")
    @ApiOperation(value = "修改某个国家的搜索引列表,需要管理员权限")
    @RequiresPermissions(PermisConstant.MODIFY_SEARCH_ENGINE)
    public Result<SearchEngine> updateSearchEngine(@PathVariable String code, @ApiParam("必须包含所有字段") @Valid @RequestBody SearchEngineAddDto engineDto) {
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.setCountryCode(code);
        searchEngine.setEngines(JSONObject.toJSONString(engineDto.getEngines()));
        return searchEngineService.updateByCountryCode(searchEngine) ? Result.ok(searchEngine) : Result.error();
    }

    @DeleteMapping("")
    @ApiOperation(value = "批量删除默认搜索引擎列表,需要管理员权限", notes = "返回删除成功的数量")
    @RequiresPermissions(PermisConstant.DELETE_SEARCH_ENGINE)
    public Result<Integer> addSearchEngine(@RequestBody List<Integer> eids) {
        int count = searchEngineService.deleteSearchEngines(eids);
        return count > 0 ? Result.ok(count) : Result.error();
    }
}
