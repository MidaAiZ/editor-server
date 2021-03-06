package net.tabplus.api.modules.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.tabplus.api.modules.dao.PermissionDAO;
import net.tabplus.api.modules.pojo.Permission;
import net.tabplus.api.modules.pojo.PermissionExample;
import net.tabplus.api.modules.vo.ListQueryVo;
import net.tabplus.api.modules.vo.ListResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionService {
    @Autowired
    private PermissionDAO permissionDAO;
    @Autowired
    private RolesPermissionsService rolesPermissionsService;

    /**
     * 创建permission
     *
     * @param permission
     * @return
     */
    public Boolean createPermission(Permission permission) {
        return permissionDAO.insert(permission) > 0;
    }

    /**
     * 创建权限列表
     *
     * @param permissions
     * @return
     */
    public int createPermissionList(List<Permission> permissions) {
        int count = 0;
        for (Permission p : permissions) {
            if (createPermission(p)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 获取权限列表
     *
     * @param queryVo
     * @return
     */
    public ListResultVo<Permission> getPermissions(ListQueryVo queryVo) {
        Page page = PageHelper.startPage(queryVo);
        permissionDAO.selectByExample(new PermissionExample());
        return new ListResultVo(page);
    }

    /**
     * 获取系统内所有权限列表
     *
     * @return
     */
    public List<Permission> getAllPermissions() {
        return permissionDAO.selectByExample(new PermissionExample());
    }

    /**
     * 通过权限id获取权限
     *
     * @param pid
     * @return
     */
    public Permission getPermissionById(Integer pid) {
        return permissionDAO.selectByPrimaryKey(pid);
    }

    /**
     * 通过权限字符串列表查询权限
     *
     * @param permissions
     * @return
     */
    public List<Permission> getPermissionsByPermiss(List<String> permissions) {
        PermissionExample ex = new PermissionExample();
        ex.createCriteria().andPermisionIn(permissions);
        return permissionDAO.selectByExample(ex);
    }

    /**
     * 通过角色id获取角色所有的权限
     *
     * @param roleId
     * @return
     */
    public List<Permission> getPermissionsByRoleId(Integer roleId) {
        return permissionDAO.selectPermissionsByRoleId(roleId);
    }

    /**
     * 根据角色id获取不具有的权限列表
     *
     * @param roleId
     * @return
     */
    public List<Permission> getMissingPermissionsByRoleId(Integer roleId) {
        return permissionDAO.selectMissingPermissionsByRoleId(roleId);
    }

    /**
     * 更新权限
     *
     * @param permission
     * @return
     */
    public Boolean updatePermission(Permission permission) {
        return permissionDAO.updateByPrimaryKeySelective(permission) > 0;
    }


    /**
     * 删除权限列表及其关联
     *
     * @param pids
     * @return
     */
    public Boolean deletePermissions(List<Integer> pids) {
        rolesPermissionsService.removeRelationsByPermissionIds(pids);
        PermissionExample example = new PermissionExample();
        example.createCriteria().andPidIn(pids);
        return permissionDAO.deleteByExample(example) > 0;
    }

    /**
     * 删除一个权限及其关联
     *
     * @param permissionId
     */
    public Boolean deletePermission(Integer permissionId) {
        List<Integer> list = new ArrayList(1);
        list.add(permissionId);
        rolesPermissionsService.removeRelationsByPermissionIds(list);
        return permissionDAO.deleteByPrimaryKey(permissionId) > 0;
    }
}
