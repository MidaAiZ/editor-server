package com.mida.chromeext.modules.controller.manage;

import com.mida.chromeext.components.shiro.PermisConstant;
import com.mida.chromeext.modules.pojo.Role;
import com.mida.chromeext.modules.service.RoleService;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.modules.vo.ListQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("manage/roles")
@Slf4j
@Api(value = "后台管理角色操作", tags = "{}")
public class RolesController {
    @Autowired
    private RoleService roleService;

    @GetMapping("")
    @ApiOperation(value = "获取角色列表，不包含权限信息")
    @RequiresPermissions(PermisConstant.SHOW_SYS_ROLE)
    public Result<List<Role>> getList(@RequestParam("当前页数") Integer pageNum, @RequestParam("每页数据量") Integer pageSize) {
        return Result.ok(roleService.getRoles(new ListQueryVo(pageNum, pageSize)));
    }

    @GetMapping("{roleId}")
    @ApiOperation(value = "获取角色id，包含角色信息，包含权限信息")
    @RequiresPermissions(PermisConstant.SHOW_SYS_ROLE)
    public Result<Role> show(@PathVariable Integer roleId) {
        return Result.ok(roleService.getRoleById(roleId));
    }

    @PostMapping("")
    @ApiOperation("新建单个角色")
    @RequiresPermissions(PermisConstant.ADD_SYS_ROLE)
    public Result<Role> create(@ApiParam("角色对象") @RequestBody Role role) {
        if (roleService.createRole(role)) {
            return Result.ok(role);
        } else {
            return Result.error();
        }
    }

    @PostMapping("list")
    @ApiOperation("新建角色列表")
    @RequiresPermissions(PermisConstant.ADD_SYS_ROLE)
    public Result<List<Role>> create(@ApiParam("角色对象") @RequestBody List<Role> roles) {
        if (roleService.createRoleList(roles) > 0) {
            return Result.ok(roles);
        } else {
            return Result.error();
        }
    }

    @PutMapping("{roleId}")
    @ApiOperation("通过角色id更新角色内容")
    @RequiresPermissions(PermisConstant.MODIFY_SYS_ROLE)
    public Result<Role> update(@PathVariable Integer roleId, @ApiParam("角色对象") @RequestBody Role role) {
        role.setRid(roleId);
        return Result.ok(roleService.updateRole(role));
    }

    @DeleteMapping()
    @ApiOperation("删除角色列表")
    @RequiresPermissions(PermisConstant.DELETE_SYS_ROLE)
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
    @RequiresPermissions(PermisConstant.ADD_PERMIS_TO_ROLE)
    public Result<Boolean> bindPermissionsToRoles(@PathVariable Integer roleId, @RequestBody List<Integer> permissionIds) {
        try {
            roleService.addPermissionsToRole(roleId, permissionIds);
            return Result.ok(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping("{roleId}/permissions")
    @ApiOperation("通过角色rid和权限pid列表接触权限绑定")
    @RequiresPermissions(PermisConstant.REMOVE_PERMIS_OFF_ROLE)
    public Result<Boolean> unbindPermissionsOffRoles(@PathVariable Integer roleId, @RequestBody List<Integer> permissionIds) {
        try {
            roleService.removePermissionsOfRole(roleId, permissionIds);
            return Result.ok(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error(e.getMessage());
        }
    }
}
