package com.mida.chromeext.modules.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.mida.chromeext.modules.dto.SearchEngineItemDto;
import com.mida.chromeext.modules.pojo.SearchEngine;
import com.mida.chromeext.modules.service.SearchEngineService;
import com.mida.chromeext.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * @author lihaoyu
 * @date 2019/10/7 19:20
 */
@RestController
@RequestMapping("search-engines")
@Api(value = "前台默认搜索引擎配置获取", tags = "{}")
public class SearchEnginesController {

    @Autowired
    SearchEngineService searchEngineService;

    @ApiOperation(value = "通过唯一国家码获取搜索引擎，如果指定的配置不存在，获取默认值", notes = "国家码可选，如果不传后端则根据请求体自动获取国家")
    @GetMapping("default")
    public Result<List<SearchEngineItemDto>> getSearchEngine(@ApiParam("国家码") @RequestParam(required = false) String code, HttpServletRequest request) {
        if (StringUtils.isEmpty(code)) {
            Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
            code = locale.getCountry();
        }
        SearchEngine searchEngine = searchEngineService.getSearchEngine(code);
        if (searchEngine == null) {
            return Result.ok(Lists.newArrayList());
        }
        return Result.ok(JSONObject.parseArray(searchEngine.getEngines(), SearchEngineItemDto.class));
    }
}
