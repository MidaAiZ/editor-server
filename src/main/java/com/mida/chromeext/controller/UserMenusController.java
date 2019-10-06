package com.mida.chromeext.controller;

import com.mida.chromeext.annotation.CurrentUser;
import com.mida.chromeext.annotation.LoginRequired;
import com.mida.chromeext.pojo.User;
import com.mida.chromeext.pojo.UserMenu;
import com.mida.chromeext.service.UserMenuService;
import com.mida.chromeext.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("user_menus")
@Api(tags = "用户菜单列表操作接口")
public class UserMenusController {

    @Autowired
    private UserMenuService userMenuService;

    @LoginRequired
    @GetMapping("")
    @ApiOperation("获取用户菜单列表")
    public Result<List<UserMenu>> getUserMenuList(@ApiIgnore @CurrentUser User user) {
        List<UserMenu> userMenuList = userMenuService.getMenuListByUserId(user.getUid());
        return Result.ok(userMenuList);
    }

    @LoginRequired
    @PostMapping("")
    @ApiOperation("添加1个用户菜单")
    public Result<UserMenu> addMenu(@ApiIgnore @CurrentUser User user, @RequestBody UserMenu userMenu) {
        userMenu = userMenuService.addOneUserMenu(user.getUid(), userMenu);
        return Result.ok(userMenu);
    }

    @LoginRequired
    @PostMapping("list")
    @ApiOperation(value = "替换用户菜单列表", notes = "此接口会覆盖用户原有菜单列表并返回替换后的新的菜单列表")
    public Result<List<UserMenu>> replaceMenuList(@ApiIgnore @CurrentUser User user,  @ApiParam("新的菜单列表") @RequestBody List<UserMenu> userMenuList) {
        userMenuService.addAndReplaceUserMenuList(user.getUid(), userMenuList);
        return Result.ok(userMenuList);
    }

    @LoginRequired
    @PutMapping("")
    @ApiOperation(value = "更新指定的用户菜单列表", notes = "返回被更新的菜单数量")
    public Result<Integer> addMenuList(@ApiIgnore @CurrentUser User user, @ApiParam("需要更新的菜单列表，包含菜单id和更新字段") @RequestBody List<UserMenu> userMenuList) {
        return Result.ok(userMenuService.updateMenuList(user.getUid(), userMenuList));
    }

    @LoginRequired
    @DeleteMapping("")
    @ApiOperation(value = "删除指定的用户菜单列表", notes = "返回被删除的菜单数量")
    public Result<Integer> delete(@ApiIgnore @CurrentUser User user, @ApiParam("菜单id数组") @RequestBody List<Long> userMenuIds) {
        return Result.ok(userMenuService.deleteUserMenus(user.getUid(), userMenuIds));
    }
}
