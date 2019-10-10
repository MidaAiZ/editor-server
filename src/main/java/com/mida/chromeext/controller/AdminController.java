package com.mida.chromeext.controller;

import com.mida.chromeext.pojo.Admin;
import com.mida.chromeext.pojo.Role;
import com.mida.chromeext.service.AdminService;
import com.mida.chromeext.service.RoleService;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.utils.ResultCode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("manage/admin")
@Slf4j
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;


    @GetMapping("profile")
    @ApiOperation("获取当前登录管理员的信息")
    public Result<Admin> profile() {
        return Result.ok(adminService.getAdminById(1));
    }

    @GetMapping("{adminId}")
    @ApiOperation("获取指定管理员的信息")
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
    public Result<Boolean> addRolesToAdmin(@PathVariable Integer adminId, @ApiParam("角色主键列表") @RequestBody List<Integer> rolesId) {
        try {
            roleService.addRolesToAdmin(adminId, rolesId);
            return Result.ok(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error();
        }
    }

    @DeleteMapping("{adminId/roles")
    @ApiOperation("删除管理员的角色列表")
    public Result<Boolean> removeRolesOfAdmin(@PathVariable Integer adminId, @ApiParam("角色主键列表") @RequestBody List<Integer> roleIds) {
        return roleService.removeRolesOfAdmin(adminId, roleIds) ? Result.ok(true) : Result.error();
    }

    @GetMapping("{adminId}/roles")
    @ApiOperation("通过管理员aid获取管理员角色和权限")
    public Result<List<Role>> getRolesByAdmin(@PathVariable Integer adminId) {
        return Result.ok(roleService.getRolesByAdminId(adminId));
    }
}
