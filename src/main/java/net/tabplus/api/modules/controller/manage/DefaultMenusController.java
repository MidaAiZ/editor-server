package net.tabplus.api.modules.controller.manage;

import com.alibaba.fastjson.JSONObject;
import net.tabplus.api.annotation.CurrentAdmin;
import net.tabplus.api.components.shiro.PermisConstant;
import net.tabplus.api.modules.dto.UserMenuItemDto;
import net.tabplus.api.modules.pojo.Admin;
import net.tabplus.api.modules.pojo.DefaultMenu;
import net.tabplus.api.modules.service.DefaultMenuService;
import net.tabplus.api.modules.vo.DefaultMenuRelVo;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController("mngDefaultMenusController")
@RequestMapping("manage/default_menus")
@Api(value = "后台不同国家的默认配置菜单列表配置", tags = "{}")
@Validated
public class DefaultMenusController {
    @Autowired
    private DefaultMenuService defaultMenuService;

    @GetMapping("")
    @ApiOperation("后台获取默认菜单配置列表，需要管理员权限")
    @RequiresPermissions(PermisConstant.SHOW_DEFAULT_MENU)
    public Result<List<DefaultMenuRelVo>> getList() {
        return Result.ok(defaultMenuService.getAllList());
    }

    @GetMapping("{code}")
    @ApiOperation("通过地区码获取一条默认菜单配置，需要管理员权限")
    @RequiresPermissions(PermisConstant.SHOW_DEFAULT_MENU)
    public Result<Map> getOne(@PathVariable String code) {
        DefaultMenu menu = defaultMenuService.getOneByCountryCode(code);
        if (menu == null) {
            return Result.error(ResultCode.NOT_FOUND.code(), "No such defaultMenu with code = " + code);
        }
        try {
            Map<String, Object> returnMap = ObjectToMap.convert(menu);
            returnMap.put("menus", JSONObject.parseArray(menu.getMenus(), UserMenuItemDto.class));
            return Result.ok(returnMap);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }


    @PostMapping("{code}")
    @ApiOperation("新建默认菜单列表，返回新建成功的列表，需要管理员权限")
    @RequiresPermissions(PermisConstant.ADD_DEFAULT_MENU)
    public Result<DefaultMenu> createList(@PathVariable String code, @RequestBody @ApiParam("新建的默认菜单配置数组") List<@Valid UserMenuItemDto> menuList, @ApiIgnore @CurrentAdmin Admin admin) {
        DefaultMenu defaultMenu = new DefaultMenu();
        defaultMenu.setCountryCode(code);
        defaultMenu.setMenus(JSONObject.toJSONString(menuList));
        try {
            return defaultMenuService.create(defaultMenu, admin) ? Result.ok(defaultMenu) : Result.error();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PutMapping("{code}")
    @ApiOperation("批量更新默认菜单列表，返回更新后的列表，需要管理员权限")
    @RequiresPermissions(PermisConstant.MODIFY_DEFAULT_MENU)
    public Result<DefaultMenu> updateList(@PathVariable String code, @RequestBody @ApiParam("更新的默认菜单配置数组，必须包含主键did") List<UserMenuItemDto> menuList) {
        try {
            DefaultMenu defaultMenu = new DefaultMenu();
            defaultMenu.setCountryCode(code);
            defaultMenu.setMenus(JSONObject.toJSONString(menuList));
            return defaultMenuService.updateByCountyCode(defaultMenu) ? Result.ok(defaultMenu) : Result.error();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping("")
    @ApiOperation("批量删除菜单列表，返回删除成功的数量，需要管理员权限")
    @RequiresPermissions(PermisConstant.DELETE_DEFAULT_MENU)
    public Result<Integer> deleteList(@RequestBody @ApiParam("需要删除的默认列表的did数组") List<Integer> idList) {
        try {
            int count = defaultMenuService.deleteList(idList);
            return count == idList.size() ? Result.ok(count) : Result.error();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
