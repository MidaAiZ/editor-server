package com.mida.chromeext.controller;

import com.mida.chromeext.pojo.Permission;
import com.mida.chromeext.service.PermissionService;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.vo.ListQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "后台管理权限操作", tags = "{}")
@RequestMapping("manage/permissions")
@RestController
@Slf4j
public class PermissionsController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("")
    @ApiOperation("获取权限列表")
    public Result<List<Permission>> getList(@ApiParam("分页参数") ListQueryVo queryVo) {
        return Result.ok(permissionService.getPermissions(queryVo));
    }

    @PostMapping("")
    @ApiOperation("新建权限")
    public Result<Permission> create(@RequestBody Permission permission) {
        if (permissionService.createPermission(permission)) {
            return Result.ok(permission);
        } else {
            return Result.error();
        }
    }

    @PostMapping("list")
    @ApiOperation("新建多条权限")
    public Result<List<Permission>> createList(@ApiParam("权限对象列表") @RequestBody List<Permission> permissions) {
        if (permissionService.createPermissionList(permissions) > 0) {
            return Result.ok(permissions);
        } else {
            return Result.error();
        }
    }

    @PutMapping("{pid}")
    @ApiOperation("更新权限")
    public Result<Permission> update(@PathVariable Integer pid, @ApiParam("permission对象") @RequestBody Permission permission) {
        permission.setPid(pid);
        if (permissionService.createPermission(permission)) {
            return Result.ok(permission);
        } else {
            return Result.error();
        }
    }

    @DeleteMapping("")
    @ApiParam("通过权限rid列表删除权限")
    public Result<Boolean> deleteList(@RequestBody List<Integer> pIds) {
        try {
            return Result.ok(permissionService.deletePermissions(pIds));
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error(e.getMessage());
        }
    }
}
