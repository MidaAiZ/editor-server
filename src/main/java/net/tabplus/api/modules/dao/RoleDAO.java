package net.tabplus.api.modules.dao;

import net.tabplus.api.modules.dao.mapper.RoleMapper;
import net.tabplus.api.modules.pojo.Role;
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
