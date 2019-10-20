package com.mida.chromeext.modules.controller.app;

import com.mida.chromeext.modules.pojo.DefaultMenu;
import com.mida.chromeext.modules.service.DefaultMenuService;
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
import java.util.Locale;

@RestController
@RequestMapping("default_menus")
@Api(value = "前台不同国家的默认配置菜单列表获取", tags = "{}")
public class DefaultMenusController {
    @Autowired
    private DefaultMenuService defaultMenuService;

    @GetMapping("default")
    @ApiOperation(value = "通过唯一国家码获取默认菜单配置，如果指定的配置不存在，则返回系统数据库中的默认记录", notes = "国家码可选，如果不传后端则根据请求体自动获取国家")
    public Result<DefaultMenu> getOneByCountryCode(@ApiParam("国家码") @RequestParam(required = false) String code, HttpServletRequest request) {
        if (StringUtils.isEmpty(code)) {
            Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
            code = locale.getCountry();
        }
        DefaultMenu menu = defaultMenuService.getOneByCountryCode(code);
        if (menu == null) {
            menu = defaultMenuService.getDefaultMenu();
        }
        return Result.ok(menu);
    }
}
