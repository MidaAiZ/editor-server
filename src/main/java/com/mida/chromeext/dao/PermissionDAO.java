package com.mida.chromeext.dao;

import com.mida.chromeext.mapper.PermissionMapper;
import com.mida.chromeext.pojo.Permission;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/22 12:47
 */
@Repository
public interface PermissionDAO extends PermissionMapper {
    List<Permission> getPermissions();
    void addpermission(Permission permission);
    void deletepermission(long pid);
    Permission getPermissionByid(Long pid);
    Permission updatePermission(Permission permission);
    void deletePermissionsByid(Long permissionid);//删除所有角色中的permission
}
