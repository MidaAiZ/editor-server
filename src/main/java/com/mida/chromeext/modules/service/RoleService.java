package com.mida.chromeext.modules.service;

import com.github.pagehelper.PageHelper;
import com.mida.chromeext.modules.dao.RoleDAO;
import com.mida.chromeext.modules.pojo.Role;
import com.mida.chromeext.modules.pojo.RoleExample;
import com.mida.chromeext.modules.vo.ListQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired PermissionService permissionService;

    @Autowired
    private AdminRoleService adminRoleService;

    /**
     * 创建角色
     *
     * @param role
     * @return Boolean
     */
    public Boolean createRole(Role role) {
        return roleDAO.insert(role) > 0;
    }

    /**
     * 创建一堆角色 哼
     *
     * @param roles
     * @return
     */
    public int createRoleList(List<Role> roles) {
        int count = 0;
        for (Role role : roles) {
            if (createRole(role)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 通过管理员id获取角色和权限
     *
     * @param adminId
     * @return
     */
    public List<Role> getRolesByAdminId(Integer adminId) {
        return roleDAO.getRolesWithPermissionsByAdminId(adminId);
    }

    /**
     * 添加角色给管理员
     *
     * @param adminId
     * @param roleIds
     * @return
     */
    public int addRolesToAdmin(Integer adminId, List<Integer> roleIds) {
        int count = 0;
        for (Integer rid : roleIds) {
            if (adminRoleService.addRelation(adminId, rid)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 移除管理员角色
     *
     * @param adminId
     * @param roleIds
     * @return
     */
    public Boolean removeRolesOfAdmin(Integer adminId, List<Integer> roleIds) {
        return rolePermissionService.removeRelations(adminId, roleIds);
    }

    /**
     * 将权限添加到角色
     *
     * @param roleId
     * @param permissionIds
     * @return
     */
    public int addPermissionsToRole(Integer roleId, List<Integer> permissionIds) {
        int count = 0;
        for (Integer pid : permissionIds) {
            if (rolePermissionService.addRelation(roleId, pid)) {
                count++;
            }
            ;
        }
        return count;
    }

    /**
     * 将权限从角色上移除
     *
     * @param roleId
     * @param permissionIds
     */
    public Boolean removePermissionsOfRole(Integer roleId, List<Integer> permissionIds) {
        return rolePermissionService.removeRelations(roleId, permissionIds);
    }

    /**
     * 获取角色列表，包含权限信息
     *
     * @param queryVo
     * @return
     */
    public List<Role> getRolesWithPermissions(ListQueryVo queryVo) {
        PageHelper.startPage(queryVo);
        return roleDAO.getRolesWithPermissions();
    }

    /**
     * 获取角色列表，不包含权限信息
     *
     * @param queryVo
     * @return
     */
    public List<Role> getRoles(ListQueryVo queryVo) {
        PageHelper.startPage(queryVo);
        RoleExample example = new RoleExample();
        return roleDAO.selectByExample(example);
    }

    /**
     * 删除一个角色，同时删除关联表记录
     *
     * @param roleId
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteRoleById(Integer roleId) {
        List<Integer> list = new ArrayList(1);
        list.add(roleId);
        rolePermissionService.removeRelationsByRoleIds(list);
        return roleDAO.deleteByPrimaryKey(roleId) > 0;
    }

    /**
     * 删除角色列表，同时删除关联表记录
     *
     * @param roleIds
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteRoles(List<Integer> roleIds) {
        rolePermissionService.removeRelationsByRoleIds(roleIds);
        RoleExample example = new RoleExample();
        example.createCriteria().andRidIn(roleIds);
        return roleDAO.deleteByExample(example) > 0;
    }

    /**
     * 通过rid获取角色
     *
     * @param rid
     * @return
     */
    public Role getRoleById(Integer rid) {
        Role role = roleDAO.selectByPrimaryKey(rid);
        if (role != null) {
            role.setPermissions(permissionService.getPermissionsByRoleId(rid));
        }
        return role;
    }

    /**
     * 通过主键更新角色
     *
     * @param role
     * @return
     */
    public Role updateRole(Role role) {
        roleDAO.updateByPrimaryKeySelective(role);
        return role;
    }
}
