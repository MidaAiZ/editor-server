package com.mida.chromeext.modules.controller.manage;

import com.mida.chromeext.annotation.CurrentAdmin;
import com.mida.chromeext.components.shiro.RoleConstant;
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
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
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

    /**
     * 添加管理员权限
     * 系统默认的超级管理员权限和管理员权限受保护，因此具有额外的权限校验
     * 除此外的角色只要具有相关权限即可添加
     * @param adminId
     * @param rolesId
     * @return
     */
    @PostMapping("{adminId}/roles")
    @ApiOperation("添加角色给管理员")
    @RequiresPermissions(PermisConstant.ADD_ROLE_TO_ADMIN)
    public Result<Boolean> addRolesToAdmin(@PathVariable Integer adminId, @ApiParam("角色主键列表") @RequestBody List<Integer> rolesId) {
        List<Role> targetRoles = roleService.getRolesByIds(rolesId);
        Subject subject = SecurityUtils.getSubject();
        // 保护系统特殊权限
        Boolean isSuper = subject.hasRole(RoleConstant.SUPER_ROLE);
        Boolean isAdmin = subject.hasRole(RoleConstant.SUPER_ROLE);
        for (Role role : targetRoles) {
            if (role.getName().equals(RoleConstant.SUPER_ROLE) && !isSuper) {
                return Result.error(ResultCode.FORBIDDEN.code(), "Operation denied");
            }
            if (role.getName().equals(RoleConstant.ADMIN_ROLE) && !(isSuper || isAdmin)) {
                return Result.error(ResultCode.FORBIDDEN.code(), "Operation denied");
            }
        }
        try {
            roleService.addRolesToAdmin(adminId, rolesId);
            return Result.ok(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error(e.getMessage());
        }
    }

    /**
     * 删除管理员权限
     * 禁止管理员删除超级管理员以及同级管理员
     * 系统初始化的第一个超级管理员受保护无法被删除
     * @param adminId
     * @param roleIds
     * @return
     */
    @DeleteMapping("{adminId}/roles")
    @ApiOperation("删除管理员的角色列表")
    @RequiresPermissions(PermisConstant.REMOVE_ROLE_OFF_ADMIN)
    public Result<Boolean> removeRolesOfAdmin(@PathVariable Integer adminId, @ApiParam("角色主键列表") @RequestBody List<Integer> roleIds) {
        List<Role> roles = roleService.getRolesByAdminId(adminId);
        Subject subject = SecurityUtils.getSubject();
        List<String> protectedRoles = new ArrayList<>();
        if (!subject.hasRole(RoleConstant.SUPER_ROLE)) {
            protectedRoles.add(RoleConstant.SUPER_ROLE);
            protectedRoles.add(RoleConstant.ADMIN_ROLE);
        }

        for (Role role : roles) {
            if (protectedRoles.contains(role.getName())) {
                return Result.error(ResultCode.FORBIDDEN.code(), "Operation denied");
            }
        }

        return roleService.removeRolesOfAdmin(adminId, roleIds) ? Result.ok(true) : Result.error();
    }
}
