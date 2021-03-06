package net.tabplus.api.modules.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import net.tabplus.api.modules.pojo.DefaultMenu;
import net.tabplus.api.modules.service.DefaultMenuService;
import net.tabplus.api.utils.Result;
import net.tabplus.api.modules.dto.UserMenuItemDto;
import net.tabplus.api.utils.LocaleHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("default_menus")
@Api(value = "前台不同国家的默认配置菜单列表获取", tags = "{}")
public class DefaultMenusController {
    @Autowired
    private DefaultMenuService defaultMenuService;

    @GetMapping("default")
    @ApiOperation(value = "通过唯一国家码获取默认菜单配置，如果指定的配置不存在，则返回系统数据库中的默认记录", notes = "国家码可选，如果不传后端则根据请求体自动获取国家")
    public Result<List<List>> getOneByCountryCode(@ApiParam("国家码") @RequestParam(required = false) String code, HttpServletRequest request) {
        if (StringUtils.isEmpty(code)) {
            code = LocaleHelper.getContextCountryCode(request);
        }
        DefaultMenu menu = defaultMenuService.getOneByCountryCode(code);
        if (menu == null) {
            return Result.ok(Lists.newArrayList());
        }
        return Result.ok(JSONObject.parseArray(menu.getMenus(), List.class));
    }
}
