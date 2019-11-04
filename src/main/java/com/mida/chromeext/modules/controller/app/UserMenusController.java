package com.mida.chromeext.modules.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.mida.chromeext.annotation.CurrentUser;
import com.mida.chromeext.annotation.LoginRequired;
import com.mida.chromeext.modules.dto.UserMenuItemDto;
import com.mida.chromeext.modules.pojo.User;
import com.mida.chromeext.modules.pojo.UserMenu;
import com.mida.chromeext.modules.service.UserMenuService;
import com.mida.chromeext.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user_menus")
@Api(value = "前台用户菜单列表操作接口", tags = "{}")
@Validated
public class UserMenusController {

    @Autowired
    private UserMenuService userMenuService;

    @LoginRequired
    @GetMapping("")
    @ApiOperation(value = "获取用户菜单列表", notes = "返回字段包括version和menu，当用户不存在菜单时返回null")
    public Result<Map> getUserMenuList(@ApiIgnore @CurrentUser User user) {
        UserMenu menu = userMenuService.getMenuItemsByUserId(user.getUid());
        if (menu == null) {
            return Result.ok(null);
        }
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("version", menu.getUpdatedAt());
        returnMap.put("menu", JSONObject.parseArray(menu.getMenus(), List.class));
        return Result.ok(returnMap);
    }

    @LoginRequired
    @PostMapping("")
    @ApiOperation("设置用户菜单列表")
    public Result<List<List<UserMenuItemDto>>> setMenu(@ApiIgnore @CurrentUser User user, @RequestBody List<List<@Valid UserMenuItemDto>> menuPages) {
        return userMenuService.update(user.getUid(), menuPages) ? Result.ok(menuPages) : Result.error();
    }
}
