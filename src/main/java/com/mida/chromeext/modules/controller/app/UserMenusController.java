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

import java.util.List;

@RestController
@RequestMapping("user_menus")
@Api(value = "前台用户菜单列表操作接口", tags = "{}")
public class UserMenusController {

    @Autowired
    private UserMenuService userMenuService;

    @LoginRequired
    @GetMapping("")
    @ApiOperation("获取用户菜单列表")
    public Result<List<UserMenuItemDto>> getUserMenuList(@ApiIgnore @CurrentUser User user) {
        UserMenu menu = userMenuService.getMenuItemsByUserId(user.getUid());
        if (menu == null) {
            return Result.ok(Lists.newArrayList());
        }
        return Result.ok(JSONObject.parseArray(menu.getMenus(), UserMenuItemDto.class));
    }

    @LoginRequired
    @PostMapping("")
    @ApiOperation("设置用户菜单列表")
    public Result<List<UserMenuItemDto>> setMenu(@ApiIgnore @CurrentUser User user,  @Validated @RequestBody List<UserMenuItemDto> menuItems) {
        return userMenuService.update(user.getUid(), menuItems) ? Result.ok(menuItems) : Result.error();
    }
}
