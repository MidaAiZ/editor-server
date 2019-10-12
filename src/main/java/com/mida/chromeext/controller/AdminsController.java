package com.mida.chromeext.controller;

import com.mida.chromeext.annotation.CurrentAdmin;
import com.mida.chromeext.dto.NewAdminDto;
import com.mida.chromeext.pojo.Admin;
import com.mida.chromeext.pojo.Role;
import com.mida.chromeext.service.AdminService;
import com.mida.chromeext.service.RoleService;
import com.mida.chromeext.shiro.PermisConstant;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("manage/admins")
@Api(value = "管理员操作类", tags = "{}")
@Slf4j
public class AdminsController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @PostMapping("")
    public Result<Admin> createAdmin(@ApiParam("管理员对象") @RequestBody NewAdminDto adminDto) {
        return Result.ok(adminService.createAdmin(adminDto));
    }

    @GetMapping("profile")
    @ApiOperation("获取当前登录管理员的信息")
    public Result<Admin> profile(@ApiIgnore @CurrentAdmin Admin admin) {
        return Result.ok(admin);
    }

    @GetMapping("profile/roles")
    @ApiOperation("获取当前管理员角色和权限")
    public Result<List<Role>> getAdminRolesById(@ApiIgnore @CurrentAdmin Admin admin) {
       return Result.ok(roleService.getRolesByAdminId(admin.getAid()));
    }

    @GetMapping("{adminId}")
    @ApiOperation("获取指定管理员的信息")
    @RequiresPermissions(PermisConstant.SHOW_ADMIN)
    public Result<Admin> getAdminById(@PathVariable Integer adminId) {
        Admin admin = adminService.getAdminById(adminId);
        if (admin != null) {
            return Result.ok(admin);
        } else {
            return Result.error(ResultCode.NOT_FOUND.code(), "there is no such admin with aid = " + adminId.toString());
        }
    }

    @PostMapping("{adminId}/roles")
    @ApiOperation("添加角色给管理员")
    @RequiresPermissions(PermisConstant.ADD_ROLE_TO_ADMIN)
    public Result<Boolean> addRolesToAdmin(@PathVariable Integer adminId, @ApiParam("角色主键列表") @RequestBody List<Integer> rolesId) {
        try {
            roleService.addRolesToAdmin(adminId, rolesId);
            return Result.ok(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error();
        }
    }

    @DeleteMapping("{adminId}/roles")
    @ApiOperation("删除管理员的角色列表")
    @RequiresPermissions(PermisConstant.REMOVE_ROLE_OFF_ADMIN)
    public Result<Boolean> removeRolesOfAdmin(@PathVariable Integer adminId, @ApiParam("角色主键列表") @RequestBody List<Integer> roleIds) {
        return roleService.removeRolesOfAdmin(adminId, roleIds) ? Result.ok(true) : Result.error();
    }

    @GetMapping("{adminId}/roles")
    @ApiOperation("通过管理员aid获取管理员角色和权限")
    @RequiresPermissions(PermisConstant.SHOW_ADMIN)
    public Result<List<Role>> getRolesByAdmin(@PathVariable Integer adminId) {
        return Result.ok(roleService.getRolesByAdminId(adminId));
    }
}
