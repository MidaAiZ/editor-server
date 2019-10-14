package com.mida.chromeext.modules.controller.manage;

import com.mida.chromeext.components.shiro.PermisConstant;
import com.mida.chromeext.components.shiro.RoleConstant;
import com.mida.chromeext.modules.pojo.DefaultMenu;
import com.mida.chromeext.modules.service.DefaultMenuService;
import com.mida.chromeext.modules.vo.ListQueryVo;
import com.mida.chromeext.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("mngDefaultMenusController")
@RequestMapping("manage/default_menus")
@Api(value = "后台不同国家的默认配置菜单列表配置", tags = "{}")
public class DefaultMenusController {
    @Autowired
    private DefaultMenuService defaultMenuService;

    @PostMapping("default")
    @ApiOperation(value = "设置系统的默认菜单列表")
    @RequiresRoles(RoleConstant.SUPER_ROLE)
    @RequiresPermissions(PermisConstant.MODIFY_DEFAULT_MENU)
    public Result<Boolean> setDefault(@ApiParam("默认菜单配置主键") @RequestParam Integer did, @ApiParam("是否默认") @RequestParam Boolean isDefault) {
        return defaultMenuService.setDefault(did, isDefault) ? Result.ok(true) : Result.error();
    }

    @GetMapping("")
    @ApiOperation("后台获取默认菜单配置列表，需要管理员权限")
    @RequiresPermissions(PermisConstant.SHOW_DEFAULT_MENU)
    public Result<List<DefaultMenu>> getList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return Result.ok(defaultMenuService.getList(new ListQueryVo(pageNum, pageSize)));
    }

    @PostMapping("list")
    @ApiOperation("批量新建默认菜单列表，返回新建成功的列表，需要管理员权限")
    @RequiresPermissions(PermisConstant.ADD_DEFAULT_MENU)
    public Result<List<DefaultMenu>> createList(@RequestBody @ApiParam("新建的默认菜单配置数组") List<DefaultMenu> menuList) {
        try {
            int count = defaultMenuService.createList(menuList);
            return count == menuList.size() ? Result.ok(menuList) : Result.error();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PutMapping("list")
    @ApiOperation("批量更新默认菜单列表，返回更新后的列表，需要管理员权限")
    @RequiresPermissions(PermisConstant.MODIFY_DEFAULT_MENU)
    public Result<List<DefaultMenu>> updateList(@RequestBody @ApiParam("更新的默认菜单配置数组，必须包含主键did") List<DefaultMenu> menuList) {
        try {
            int count = defaultMenuService.updateList(menuList);
            return count == menuList.size() ? Result.ok(menuList) : Result.error();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping("list")
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
