package com.mida.chromeext.modules.dao;

import com.mida.chromeext.modules.dao.mapper.PermissionMapper;
import com.mida.chromeext.modules.pojo.Permission;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/22 12:47
 */
@Repository
public interface PermissionDAO extends PermissionMapper {
    /**
     * 根据角色ID搜索该角色所具有的权限列表
     * @param roldId
     * @return
     */
    List<Permission> selectPermissionsByRoleId(Integer roldId);

    /**
     * 根据角色ID搜索该角色不具有的权限列表
     * @param roleId
     * @return
     */
    List<Permission> selectMissingPermissionsByRoleId(Integer roleId);
}
