package com.mida.chromeext.dao;

import com.mida.chromeext.mapper.PermissionMapper;
import com.mida.chromeext.pojo.Permission;
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
