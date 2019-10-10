package com.mida.chromeext.controller;

import com.mida.chromeext.pojo.Role;
import com.mida.chromeext.service.RoleService;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.vo.ListQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("后台管理角色操作")
@RequestMapping("manage/roles")
@RestController
@Slf4j
public class RolesController {
    @Autowired
    private RoleService roleService;

    @GetMapping("")
    @ApiOperation("获取角色列表，包含权限信息")
    public Result<List<Role>> getList(@ApiParam("分页参数") ListQueryVo queryVo) {
        return Result.ok(roleService.getRoles(queryVo));
    }

    @PostMapping("")
    @ApiOperation("新建角色")
    public Result<Role> create(@ApiParam("角色对象") @RequestBody Role role) {
        if (roleService.createRole(role)) {
            return Result.ok(role);
        } else {
            return Result.error();
        }
    }

    @PostMapping("list")
    @ApiOperation("新建角色列表")
    public Result<List<Role>> create(@ApiParam("角色对象") @RequestBody List<Role> roles) {
        if (roleService.createRoleList(roles) > 0) {
            return Result.ok(roles);
        } else {
            return Result.error();
        }
    }

    @PutMapping("{roleId}")
    @ApiOperation("通过角色id更新角色内容")
    public Result<Role> update(@PathVariable Integer roleId, @ApiParam("角色对象") @RequestBody Role role) {
        role.setRid(roleId);
        return Result.ok(roleService.updateRole(role));
    }

    @DeleteMapping()
    @ApiOperation("删除角色列表")
    public Result<Boolean> deleteList(@ApiParam("角色id列表") @RequestBody List<Integer> roleIds) {
        try {
            roleService.deleteRoles(roleIds);
            return Result.ok();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("{roleId}/permissions")
    @ApiOperation("通过角色id和权限id列表绑定权限到角色")
    public Result<Boolean> bindPermissionsToRoles(@PathVariable Integer roleId, @RequestBody List<Integer> permissionIds) {
        try {
            roleService.bindPermissionsToRole(roleId, permissionIds);
            return Result.ok(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping("{roleId}/permissions")
    @ApiOperation("通过角色rid和权限pid列表接触权限绑定")
    public Result<Boolean> unbindPermissionsOffRoles(@PathVariable Integer roleId, @RequestBody List<Integer> permissionIds) {
        try {
            roleService.unbindPermissionsOfRole(roleId, permissionIds);
            return Result.ok(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error(e.getMessage());
        }
    }
}
