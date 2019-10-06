package com.mida.chromeext.controller;

import com.mida.chromeext.pojo.DefaultMenu;
import com.mida.chromeext.service.DefaultMenuService;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.vo.ListQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("default_menus")
@Api(tags = "不同国家的默认配置菜单列表配置")
public class DefaultMenusController {
    @Autowired
    private DefaultMenuService defaultMenuService;

    @GetMapping("list")
    @ApiOperation("后台获取默认菜单配置列表，需要管理员权限")
    public Result<List<DefaultMenu>> getList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return Result.ok(defaultMenuService.getList(new ListQueryVo(pageNum, pageSize)));
    }

    @GetMapping("default")
    @ApiOperation("通过唯一国家码获取默认菜单配置，如果指定的配置不存在，则返回系统数据库中的默认记录")
    public Result<DefaultMenu> getOneByCountryCode(@ApiParam("国家码") @RequestParam String code) {
        DefaultMenu menu = defaultMenuService.getOneByCountryCode(code);
        if (menu == null) {
            menu = defaultMenuService.getDefaultMenu();
        }
        return Result.ok(menu);
    }

    @PostMapping("list")
    @ApiOperation("批量新建默认菜单列表，返回新建成功的列表，需要管理员权限")
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
    public Result<Integer> deleteList(@RequestBody @ApiParam("需要删除的默认列表的did数组") List<Integer> idList) {
        try {
            int count = defaultMenuService.deleteList(idList);
            return count == idList.size() ? Result.ok(count) : Result.error();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
