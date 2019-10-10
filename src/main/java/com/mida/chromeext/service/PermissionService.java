package com.mida.chromeext.service;

import com.github.pagehelper.PageHelper;
import com.mida.chromeext.dao.PermissionDAO;
import com.mida.chromeext.pojo.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    @Autowired
    PermissionDAO permissionDAO;

    public Permission createPermission(Permission permission) {
        permissionDAO.insert(permission);
        return permission;
    }

    public void deletePermission(Long permissionId) {
        permissionDAO.deletepermission(permissionId);
    }

    public List<Permission> getPermissions() {
        return permissionDAO.getPermissions();
    }

    public Permission getPermissionByid(Long pid) {
        return permissionDAO.getPermissionByid(pid);
    }

    public Permission updatePermission(Permission permission) {
        permissionDAO.updatePermission(permission);
        return permission;
    }

    public List<Permission> getPagePermissions(int pagenum, int pagesize) {
        PageHelper.startPage(pagenum, pagesize);
        List<Permission> list = permissionDAO.getPermissions();
        return list;
    }

    public void deletePermissions(Long pid) {
        permissionDAO.deletePermissionsByid(pid);
    }
}
