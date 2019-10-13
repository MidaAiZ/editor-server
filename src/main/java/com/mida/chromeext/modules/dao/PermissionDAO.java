package com.mida.chromeext.modules.dao;

import com.mida.chromeext.modules.dao.mapper.PermissionMapper;
import com.mida.chromeext.modules.pojo.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/9/22 12:47
 */
@Repository
public interface PermissionDAO extends PermissionMapper {
    List<Permission> selectPermissionsByRoleId(Integer roldId);
}
