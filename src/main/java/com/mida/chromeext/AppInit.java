package com.mida.chromeext;

import com.mida.chromeext.annotation.SysPermission;
import com.mida.chromeext.annotation.SysRole;
import com.mida.chromeext.components.shiro.PermisConstant;
import com.mida.chromeext.components.shiro.RoleConstant;
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
import java.util.*;

@Component
public class AppInit {
    @Autowired
    PermissionService permissionService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;

    @PostConstruct
    public void init() {
        /**
         * 先创创建权限，再创建角色，最后创建超级管理员
         */
        initPermissions();
        initRoles();
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
            for (Field field : fields) {
                if (!allSysPermissions.contains(field.get(claszInner))) {
                    Permission p = new Permission();
                    p.setPermision((String) field.get(claszInner));
                    p.setDescription(field.getAnnotation(SysPermission.class).desc());
                    p.setUrl(field.getAnnotation(SysPermission.class).url());
                    missingPermissions.add(p);
                }
                System.out.println(field.getName() + " " + field.get(claszInner));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        permissionService.createPermissionList(missingPermissions);
    }


    /**
     * 初始化角色，创建超级管理员角色
     */
    private void initRoles() {
        try {
            // 系统默认角色
            Map<String, Field> roleNameMap = new HashMap();

            // 获取权限所有变量的值
            Class claszInner = RoleConstant.class.getClass();
            Field[] fields = RoleConstant.class.getFields();
            for (Field field : fields) {
                roleNameMap.put((String) field.get(claszInner), field);
            }

            // 从数据库中检索已存在角色
            List<String> roleNames = new ArrayList();
            roleNames.addAll(roleNameMap.keySet());
            List<Role> roles = roleService.getRolesByNames(roleNames);
            List<String> existRoleNames = new ArrayList();
            for (Role role : roles) {
                existRoleNames.add(role.getName());
            }

            for (Map.Entry<String, Field> entry : roleNameMap.entrySet()) {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                // 数据库中不存在这个角色
                if (!existRoleNames.contains(entry.getKey())) {
                    // 创建角色
                    Role role = new Role();
                    Field field = entry.getValue();
                    role.setName((entry.getKey()));
                    role.setDescription(field.getAnnotation(SysRole.class).desc());
                    roleService.createRole(role);

                    // 获取这个角色所拥有的所有权限
                    List<String> rolePermissions = new ArrayList<>();
                    Class pClaszInner = PermisConstant.class.getClass();
                    Field[] pFields = PermisConstant.class.getFields();
                    for (Field pField : pFields) {
                        if (Arrays.asList(pField.getAnnotation(SysPermission.class).roles()).contains(entry.getKey())) {
                            rolePermissions.add((String) pField.get(pClaszInner));
                        }
                    }

                    // 赋予权限
                    List<Permission> existPs = permissionService.getPermissionsByPermiss(rolePermissions);
                    List<Integer> psIds = new ArrayList<>();
                    for (Permission p : existPs) {
                        psIds.add(p.getPid());
                    }
                    roleService.addPermissionsToRole(role.getRid(), psIds);

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * 初始化超级管理员
     */
    private void initSuperAdmin() {
        List<String> roleNames = new ArrayList<>(1);
        roleNames.add(RoleConstant.SUPER_ROLE);
        NewAdminDto superAdmin;
        List<Admin> superAdmins = adminService.getAdminListByRoleNames(new ListQueryVo(1, 1), roleNames);
        Role superRole = roleService.getRoleByName(RoleConstant.SUPER_ROLE);

        if (superAdmins == null || superAdmins.isEmpty()) {
            superAdmin = new NewAdminDto();
            superAdmin.setNumber("Root");
            superAdmin.setPassword("SysRoot");
            superAdmin.setEmail("1036570699@qq.com");

            List<Integer> roleIds = new ArrayList<>();
            roleIds.add(superRole.getRid());
            superAdmin.setRoleIds(roleIds);
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
