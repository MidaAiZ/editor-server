package net.tabplus.api.modules.dao;

import net.tabplus.api.modules.dao.mapper.AdminMapper;
import net.tabplus.api.modules.pojo.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/15 19:59
 */
@Repository
public interface AdminDAO extends AdminMapper {
    Admin selectAdminByNumber(String number);

    Admin selectAdminByRoleName(String roleName);

    List<Admin> selectAdminByRoleNames(List<String> roleNames);
}
