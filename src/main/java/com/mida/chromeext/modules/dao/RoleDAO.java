package com.mida.chromeext.modules.dao;

import com.mida.chromeext.modules.dao.mapper.RoleMapper;
import com.mida.chromeext.modules.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/22 12:47
 */
@Repository
public interface RoleDAO extends RoleMapper {
    List<Role> getRolesWithPermissions();

    List<Role> getRolesWithPermissionsByAdminId(Integer adminId);
}
