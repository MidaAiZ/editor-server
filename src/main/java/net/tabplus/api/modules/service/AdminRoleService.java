package net.tabplus.api.modules.service;

import net.tabplus.api.modules.pojo.AdminRole;
import net.tabplus.api.modules.pojo.AdminRoleExample;
import net.tabplus.api.modules.dao.AdminRoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员-角色多对多关联
 */
@Service
public class AdminRoleService {
    @Autowired
    private AdminRoleDAO adminRoleDAO;

    /**
     * 绑定管理员-角色关系
     *
     * @param adminId
     * @param roleId
     * @return Boolean
     */
    public Boolean addRelation(Integer adminId, Integer roleId) {
        AdminRole ar = new AdminRole();
        ar.setRoleId(roleId);
        ar.setAdminId(adminId);
        return adminRoleDAO.insert(ar) > 0;
    }

    /**
     * 根据管理员id和角色id列表解除关系
     *
     * @param adminId
     * @param roleIds
     * @return Boolean
     */
    public Boolean removeRelations(Integer adminId, List<Integer> roleIds) {
        AdminRoleExample arExample = new AdminRoleExample();
        arExample.createCriteria().andAdminIdEqualTo(adminId).andRoleIdIn(roleIds);
        return adminRoleDAO.deleteByExample(arExample) > 0;
    }

    /**
     * 通过管理员ID列表删除所有关联记录
     *
     * @param adminIds
     * @return Boolean
     */
    public Boolean removeRelationsByAdminIds(List<Integer> adminIds) {
        AdminRoleExample arExample = new AdminRoleExample();
        arExample.createCriteria().andAdminIdIn(adminIds);
        return adminRoleDAO.deleteByExample(arExample) > 0;
    }

    /**
     * 通过角色ID列表删除所有关联字段
     *
     * @param roleIds
     * @return Boolean
     */
    public Boolean removeRelationsByRoleIds(List<Integer> roleIds) {
        AdminRoleExample arExample = new AdminRoleExample();
        arExample.createCriteria().andRoleIdIn(roleIds);
        return adminRoleDAO.deleteByExample(arExample) > 0;
    }

    /**
     * 判断是否拥有某个管理员-角色搭配组合
     */
    public Boolean hasRelation(Integer adminId, Integer roleId) {
        AdminRoleExample ex = new AdminRoleExample();
        ex.createCriteria().andAdminIdEqualTo(adminId).andRoleIdEqualTo(roleId);
        return adminRoleDAO.countByExample(ex) > 0;
    }
}
