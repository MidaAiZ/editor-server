package com.mida.chromeext.controller;

import com.mida.chromeext.pojo.Permission;
import com.mida.chromeext.service.PermissionService;
import com.mida.chromeext.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("后台管理权限操作")
@RequestMapping("manage/permissions")
@RestController
public class PermissionsController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("")
    public Result<List<Permission>> getList() {
        return Result.ok(permissionService.getPermissions());
    }

    @PostMapping("")
    public Result<Permission> create(@RequestBody Permission permission) {
        return Result.ok(permissionService.createPermission(permission));
    }
}
