package com.mida.chromeext.controller;

import com.mida.chromeext.dto.SearchEngineAddDto;
import com.mida.chromeext.pojo.SearchEngine;
import com.mida.chromeext.service.SearchEngineService;
import com.mida.chromeext.utils.NumConst;
import com.mida.chromeext.utils.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * @author lihaoyu
 * @date 2019/10/7 19:20
 */
@RestController
@RequestMapping("search-engine")
public class SearchEngineController {

    @Autowired
    SearchEngineService searchEngineService;


    @ApiOperation(value = "通过唯一国家码获取搜索引擎，如果指定的配置不存在，获取默认值", notes = "国家码可选，如果不传后端则根据请求体自动获取国家")
    @GetMapping("")
    public Result<SearchEngine> getSearchEngine(@ApiParam("国家码") @RequestParam(required = false) String code, HttpServletRequest request) {
        if (StringUtils.isEmpty(code)) {
            Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
            code = locale.getCountry();
        }
        SearchEngine searchEngine = searchEngineService.getSearchEngine(code);
        return Result.ok(searchEngine);
    }

    @ApiOperation(value = "获取所有国家的搜索引擎,需要管理员权限")
    @GetMapping("list")
    public Result<List<SearchEngine>> listAllSearchEngine() {
        return Result.ok(searchEngineService.listAllSearchEngine());
    }

    @ApiOperation(value = "添加某个国家的搜索引擎,需要管理员权限")
    @PostMapping
    public Result<SearchEngine> addSearchEngine(@ApiParam("必须包含所有字段") @RequestBody SearchEngineAddDto dto) {
        SearchEngine searchEngine = searchEngineService.addSearchEngine(dto);
        if (searchEngine == null) {
            return Result.error("已经存在，不能添加");
        }
        return Result.ok(searchEngine);
    }

    @ApiOperation(value = "删除某个国家的搜索引擎,需要管理员权限")
    @DeleteMapping
    public Result addSearchEngine(@ApiParam("国家码") String code) {
        int affectRow = searchEngineService.deleteSearchEngine(code);
        if (affectRow == NumConst.NUM1) {
            return Result.ok("删除成功");
        }
        return Result.error("不存在，删除失败");
    }

    @ApiOperation(value = "修改某个国家的搜索引擎,需要管理员权限")
    @PutMapping
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
