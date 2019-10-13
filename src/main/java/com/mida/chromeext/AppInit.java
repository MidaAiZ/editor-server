package com.mida.chromeext;

import com.mida.chromeext.components.shiro.PermisConstant;
import com.mida.chromeext.modules.dto.NewAdminDto;
import com.mida.chromeext.modules.pojo.Admin;
import com.mida.chromeext.modules.pojo.Permission;
import com.mida.chromeext.modules.pojo.Role;
import com.mida.chromeext.modules.service.AdminService;
import com.mida.chromeext.modules.service.PermissionService;
import com.mida.chromeext.modules.service.RoleService;
import com.mida.chromeext.modules.vo.ListQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class AppInit {
    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    PermissionService permissionService;

    @PostConstruct
    public void init() {
        /**
         * 先创创建权限，再创建角色，最后创建超级管理员
         */
        initPermissions();
        initSuperRole();
        initSuperAdmin();
    }


    /**
     * 初始化权限
     */
    private void initPermissions() {
        Iterator<Permission> it = permissionService.getAllPermissions().iterator();
        List<String> allSysPermissions = new ArrayList<>();
        while (it.hasNext()) {
            allSysPermissions.add(it.next().getPermision());
        }

        List<Permission> missingPermissions = new ArrayList<>();

        //获取权限所有变量的值
        Class claszInner = PermisConstant.class.getClass();
        Field[] fields = PermisConstant.class.getFields();
        try {
            for( Field field : fields ){
                if (!allSysPermissions.contains(field.get(claszInner))) {
                    Permission p = new Permission();
                    p.setPermision((String) field.get(claszInner));
                    p.setDescription((String) field.get(claszInner));
                    p.setUrl("manage");
                    missingPermissions.add(p);
                }
                System.out.println( field.getName() + " " + field.get(claszInner) );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        permissionService.createPermissionList(missingPermissions);
    }


    /**
     * 初始化角色，创建超级管理员角色
     */
    private void initSuperRole() {
        List<String> roleNames = new ArrayList<>(1);
        roleNames.add(PermisConstant.SUPER_ROLE);
        List<Role> roles = roleService.getRolesByNames(roleNames);
        // 超级管理员角色
        Role superRole;
        if (roles == null || roles.isEmpty()) {
            superRole = new Role();
            superRole.setName(PermisConstant.SUPER_ROLE);
            superRole.setDescription("超级管理员爸爸，很厉害的那种");
            roleService.createRole(superRole);
            Iterator<Permission> it = permissionService.getAllPermissions().iterator();
            List<Integer> psIds = new ArrayList<>();
            while (it.hasNext()) {
                psIds.add(it.next().getPid());
            }
            roleService.addRolesToAdmin(superRole.getRid(), psIds);
        } else {
            // 检查系统第一个超级管理员是否拥有所有权限，没有则补上
            superRole = roles.get(0);
            List<Integer> psIds = new ArrayList();
            Iterator<Permission> missingPermisionsIt = permissionService.getMissingPermissionsByRoleId(superRole.getRid()).iterator();
            while (missingPermisionsIt.hasNext()) { psIds.add(missingPermisionsIt.next().getPid()); }
            roleService.addPermissionsToRole(superRole.getRid(), psIds);
        }
    }

    /**
     * 初始化超级管理员
     */
    private void initSuperAdmin() {
        List<String> roleNames = new ArrayList<>(1);
        roleNames.add(PermisConstant.SUPER_ROLE);
        NewAdminDto superAdmin;
        List<Admin> superAdmins = adminService.getAdminListByRoleNames(new ListQueryVo(1, 1), roleNames);
        Role superRole = roleService.getRolesByNames(roleNames).get(0);

        if (superAdmins == null || superAdmins.isEmpty()) {
            superAdmin = new NewAdminDto();
            superAdmin.setNumber("Root");
            superAdmin.setPassword("SysRoot");
            superAdmin.setEmail("1036570699@qq.com");

            List<Integer> roleIds = new ArrayList<>();
            roleIds.add(superRole.getRid());
            superAdmin.setRoleIds(roleIds);;

            adminService.createAdmin(superAdmin);
        } else {
            if (!roleService.hasRoleOfAdminByRId(superAdmins.get(0).getAid(), superRole.getRid())) {
                List<Integer> roleIds = new ArrayList<>(1);
                roleIds.add(superRole.getRid());
                roleService.addRolesToAdmin(superAdmins.get(0).getAid(), roleIds);
            }
        }
    }
}
