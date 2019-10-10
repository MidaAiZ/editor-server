package com.mida.chromeext.dao;

import com.mida.chromeext.mapper.RoleMapper;
import com.mida.chromeext.pojo.Role;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/22 12:47
 */
@Repository
public interface RoleDAO extends RoleMapper {
    List<Role> getRoles();
    void addRole(Role role);
    void deleteById(long rid);//删除一个角色
    void addRolePermission(long roleId,long permissionId);
    void deleteRolePermission(long roleId,long permissionId);//删除一个角色和一个权限的关联
    void deleteroles(long roleid);//删除一个角色的所有权限关联
    Role getRoleById(long roleid);
    void updateRole(Role role);
}
