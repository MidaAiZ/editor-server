package net.tabplus.api.modules.service;

import com.github.pagehelper.PageHelper;
import net.tabplus.api.components.shiro.RoleConstant;
import net.tabplus.api.modules.dao.RoleDAO;
import net.tabplus.api.modules.pojo.Admin;
import net.tabplus.api.modules.pojo.Role;
import net.tabplus.api.modules.pojo.RoleExample;
import net.tabplus.api.modules.vo.ListQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    PermissionService permissionService;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private RolesPermissionsService rolesPermissionsService;
    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

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
     * 通过角色名获取角色
     *
     * @param name
     * @return
     */
    public Role getRoleByName(String name) {
        RoleExample ex = new RoleExample();
        ex.createCriteria().andNameEqualTo(name);
        List<Role> roles = roleDAO.selectByExample(ex);
        if (roles == null || roles.isEmpty()) {
            return null;
        }
        return roles.get(0);
    }

    /**
     * 通过角色名列表查询角色
     *
     * @param roleNames
     * @return
     */
    public List<Role> getRolesByNames(List<String> roleNames) {
        RoleExample ex = new RoleExample();
        ex.createCriteria().andNameIn(roleNames);
        ex.setOrderByClause("rid ASC");
        return roleDAO.selectByExample(ex);
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
     * 通过角色id判断是否有某个角色
     */
    public boolean hasRoleOfAdminByRId(Integer adminId, Integer roleId) {
        return adminRoleService.hasRelation(adminId, roleId);
    }

    /**
     * 移除管理员角色
     *
     * @param adminId
     * @param roleIds
     * @return
     */
    public Boolean removeRolesOfAdmin(Integer adminId, List<Integer> roleIds) {
        Role superRole = roleService.getRoleByName(RoleConstant.SUPER_ROLE);
        // 禁止移除系统默认超级管理员角色
        if (roleIds.contains(superRole.getRid())) {
            Admin superAdmin = adminService.getFirstSuperAdmin();
            if (superAdmin != null && superAdmin.getAid().equals(adminId)) {
                return false;
            }
        }
        return adminRoleService.removeRelations(adminId, roleIds);
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
            if (rolesPermissionsService.addRelation(roleId, pid)) {
                count++;
            }
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
        return rolesPermissionsService.removeRelations(roleId, permissionIds);
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
     * @return
     */
    public List<Role> getRoles() {
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
        rolesPermissionsService.removeRelationsByRoleIds(list);
        return roleDAO.deleteByPrimaryKey(roleId) > 0;
    }

    /**
     * 删除角色列表，同时删除关联表记录
     *
     * @param roleIds
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteRoles(List<Integer> roleIds) {
        rolesPermissionsService.removeRelationsByRoleIds(roleIds);
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
     * 通过rid列表获取角色
     *
     * @param rids
     * @return
     */
    public List<Role> getRolesByIds(List<Integer> rids) {
        RoleExample rx = new RoleExample();
        rx.createCriteria().andRidIn(rids);
        return roleDAO.selectByExample(rx);
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
