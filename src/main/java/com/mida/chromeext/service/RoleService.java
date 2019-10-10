package com.mida.chromeext.service;

import com.github.pagehelper.PageHelper;
import com.mida.chromeext.dao.RoleDAO;
import com.mida.chromeext.dao.RolePermissionDAO;
import com.mida.chromeext.pojo.Role;
import com.mida.chromeext.pojo.RolePermission;
import com.mida.chromeext.pojo.RolePermissionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private RolePermissionDAO rolePermissionDAO;

    public Role createRole(Role role) {
        roleDAO.insert(role);
        return role;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteRoleById(Integer roleId) {
        roleDAO.deleteById(roleId);
        RolePermissionExample example = new RolePermissionExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        rolePermissionDAO.deleteByExample(example);
    }

    // 将权限关联到role
    public int bindPermissionsToRole(Integer roleId, List<Integer> permissionIds) {
        int count = 0;
        for(Integer pid:permissionIds) {
            RolePermission rp = new RolePermission();
            rp.setRoleId(roleId);
            rp.setPermissionId(pid);
            rolePermissionDAO.insert(rp);
            count++;
        }
        return count;
    }

    // 将权限从role上解绑
    public void undbindPermissionsOfRole(Integer roleId, List<Integer> permissionIds) {
        for(Integer pid:permissionIds) {
            RolePermissionExample example = new RolePermissionExample();
            example.createCriteria().andPermissionIdEqualTo(pid).andRoleIdEqualTo(roleId);
            rolePermissionDAO.deleteByExample(example);
        }
    }

    // 获取权限
    public List<Role> getRoles() {
        List<Role> roles = roleDAO.getRoles();
        return roles;
    }

    public List<Role> getpagerole(int pagenum, int pagesize) {
        PageHelper.startPage(pagenum,pagesize);
        List<Role> list=roleDAO.getRoles();
        return list;
    }

    public void deleteroles(Long roleid) {
        roleDAO.deleteroles(roleid);
    }

    public Role getrolebyid(Long roleid) {
        return roleDAO.getRoleById(roleid);
    }

    public Role updateRole(Role r) {
        roleDAO.updateRole(r);
        return r;
    }
}
