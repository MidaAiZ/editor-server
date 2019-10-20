package com.mida.chromeext.modules.controller.manage;

import com.mida.chromeext.components.shiro.PermisConstant;
import com.mida.chromeext.components.shiro.RoleConstant;
import com.mida.chromeext.modules.pojo.Role;
import com.mida.chromeext.modules.service.RoleService;
import com.mida.chromeext.modules.vo.ListQueryVo;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
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

    /**
     * 删除系统角色
     * 系统默认的超级管理员和管理员角色禁止删除
     *
     * @param roleIds
     * @return
     */
    @DeleteMapping()
    @ApiOperation("删除角色列表")
    @RequiresPermissions(PermisConstant.DELETE_SYS_ROLE)
    public Result<Boolean> deleteList(@ApiParam("角色id列表") @RequestBody List<Integer> roleIds) {
        List<Role> roles = roleService.getRolesByIds(roleIds);
        for (Role role : roles) {
            if (role.getName().equals(RoleConstant.SUPER_ROLE) || role.getName().equals(RoleConstant.ADMIN_ROLE)) {
                return Result.error(ResultCode.FORBIDDEN.code(), "Illegal Operation");
            }
        }
        try {
            return roleService.deleteRoles(roleIds) ? Result.ok(true) : Result.error();
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
            return roleService.addPermissionsToRole(roleId, permissionIds) > 0 ? Result.ok(true) : Result.error();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping("{roleId}/permissions")
    @ApiOperation("通过角色rid和权限pid列表接触权限绑定")
    @RequiresPermissions(PermisConstant.REMOVE_PERMIS_OFF_ROLE)
    public Result<Boolean> unbindPermissionsOffRoles(@PathVariable Integer roleId, @RequestBody List<Integer> permissionIds) {
        Role role = roleService.getRoleById(roleId);
        // 禁止删除超级管理员权限
        if (role.getName().equals(RoleConstant.SUPER_ROLE)) {
            return Result.error(ResultCode.FORBIDDEN.code(), "Illegal Operation");
        }
        // 禁止非超级管理员删除管理员权限
        Subject subject = SecurityUtils.getSubject();
        if (role.getName().equals(RoleConstant.ADMIN_ROLE) && !subject.hasRole(RoleConstant.SUPER_ROLE)) {
            return Result.error(ResultCode.FORBIDDEN.code(), "Illegal Operation");
        }
        try {
            roleService.removePermissionsOfRole(roleId, permissionIds);
            return Result.ok(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error(e.getMessage());
        }
    }
}
