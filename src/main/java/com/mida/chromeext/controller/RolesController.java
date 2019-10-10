package com.mida.chromeext.controller;

import com.mida.chromeext.pojo.Permission;
import com.mida.chromeext.pojo.Role;
import com.mida.chromeext.service.RoleService;
import com.mida.chromeext.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("后台管理角色操作")
@RequestMapping("manage/roles")
@RestController
public class RolesController {
    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public Result<List<Role>> getList() {
        return Result.ok(roleService.getRoles());
    }

    @PostMapping("")
    public Result<Role> create(@RequestBody Role role) {
        return Result.ok(roleService.createRole(role));
    }

    @PostMapping("{roleId}/permissions")
    public Result<Boolean> bindRolesAndPermissions(@PathVariable Integer roleId, @RequestBody List<Integer> permissionIds) {
        return Result.ok(roleService.correlationPermissions(roleId, permissionIds));
    }
}
