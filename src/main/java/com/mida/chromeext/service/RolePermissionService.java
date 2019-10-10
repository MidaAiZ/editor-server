package com.mida.chromeext.service;

import com.mida.chromeext.dao.RolePermissionDAO;
import com.mida.chromeext.pojo.RolePermission;
import com.mida.chromeext.pojo.RolePermissionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionService {
    @Autowired
    private RolePermissionDAO rolePermissionDAO;

    /**
     * 绑定角色-权限关系
     *
     * @param roleId
     * @param permissionId
     * @return Boolean
     */
    public Boolean addRelation(Integer roleId, Integer permissionId) {
        RolePermission rp = new RolePermission();
        rp.setRoleId(roleId);
        rp.setPermissionId(permissionId);
        return rolePermissionDAO.insert(rp) > 0;
    }

    /**
     * 根据角色id和权限id列表解除关系
     *
     * @param roleId
     * @param permissionIds
     * @return Boolean
     */
    public Boolean removeRelations(Integer roleId, List<Integer> permissionIds) {
        RolePermissionExample rpExample = new RolePermissionExample();
        rpExample.createCriteria().andRoleIdEqualTo(roleId).andPermissionIdIn(permissionIds);
        return rolePermissionDAO.deleteByExample(rpExample) > 0;
    }

    /**
     * 通过角色ID列表删除所有关联记录
     *
     * @param roleIds
     * @return Boolean
     */
    public Boolean removeRelationsByRoleIds(List<Integer> roleIds) {
        RolePermissionExample rpExample = new RolePermissionExample();
        rpExample.createCriteria().andRoleIdIn(roleIds);
        return rolePermissionDAO.deleteByExample(rpExample) > 0;
    }

    /**
     * 通过权限ID列表删除所有关联字段
     *
     * @param permissionIds
     * @return Boolean
     */
    public Boolean removeRelationsByPermissionIds(List<Integer> permissionIds) {
        RolePermissionExample rpExample = new RolePermissionExample();
        rpExample.createCriteria().andPermissionIdIn(permissionIds);
        return rolePermissionDAO.deleteByExample(rpExample) > 0;
    }
}
