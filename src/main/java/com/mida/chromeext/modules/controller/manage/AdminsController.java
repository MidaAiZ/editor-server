package com.mida.chromeext.modules.controller.manage;

import com.mida.chromeext.annotation.CurrentAdmin;
import com.mida.chromeext.modules.dto.NewAdminDto;
import com.mida.chromeext.modules.pojo.Admin;
import com.mida.chromeext.modules.pojo.Role;
import com.mida.chromeext.modules.service.AdminService;
import com.mida.chromeext.modules.service.RoleService;
import com.mida.chromeext.components.shiro.PermisConstant;
import com.mida.chromeext.modules.vo.ListQueryVo;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("manage/admins")
@Api(value = "后台管理员操作类", tags = "{}")
@Slf4j
public class AdminsController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @GetMapping("profile")
    @ApiOperation("获取当前登录管理员的信息")
    @RequiresAuthentication
    public Result<Admin> profile(@ApiIgnore @CurrentAdmin Admin admin) {
        return Result.ok(admin);
    }

    @GetMapping("profile/roles")
    @ApiOperation("获取当前管理员角色和权限")
    @RequiresAuthentication
    public Result<List<Role>> getAdminRolesById(@ApiIgnore @CurrentAdmin Admin admin) {
       return Result.ok(roleService.getRolesByAdminId(admin.getAid()));
    }

    @PostMapping("")
    @ApiOperation("创建管理员，需要相关权限")
    @RequiresPermissions(PermisConstant.ADD_ADMIN)
    public Result<Admin> createAdmin(@ApiParam("管理员对象") @RequestBody NewAdminDto adminDto) {
        return Result.ok(adminService.createAdmin(adminDto));
    }

    @GetMapping("")
    @ApiOperation("获取管理员列表，需要相关权限")
    @RequiresPermissions(PermisConstant.SHOW_ADMIN)
    public Result<List<Admin>> getAdmins(@ApiParam("当前页数") Integer pageNum, @ApiParam("分页大小") Integer pageSize) {
        return Result.ok(adminService.getAdminList(new ListQueryVo(pageNum, pageSize)));
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
}
